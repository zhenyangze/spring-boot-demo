#!/bin/bash
# 初始化开发环境服务器

# 检查当前是否root用户
if [[ `whoami` != "root" ]];then
  echo "请使用root用户运行此脚本"
  exit 1
fi

# 创建demofile用户
if [[ `cat /etc/passwd | cut -f1 -d':' | grep -w "demofile" -c` -le 0 ]];then
  adduser demofile
  echo "demo" | passwd --stdin demofile
fi

# 关闭防火墙
systemctl stop firewalld
systemctl disable firewalld

# 安装net-tools
yum -y install net-tools

# 获取本地ip
regex_ip="^(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|[1-9]?[0-9])(\.(2[0-4][0-9]|25[0-5]|1[0-9][0-9]|[1-9]?[0-9])){3}$"
# 尝试获取本地ip
local_ip=`/sbin/ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:"`
# 如果获取ip失败，读取用户输入ip
while [[ `echo $local_ip | grep -E $regex_ip` == "" ]]
do
  echo "请输入本地ip:"
  read -p "本地ip:" -r local_ip
done

# 读取docker镜像加速地址
regex_url="^(ht|f)tp(s?)\:\/\/[0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*(:(0-9)*)*(\/?)([a-zA-Z0-9\-\.\?\,\'\/\\\+&amp;%\$#_]*)?$"
while [[ `echo $docker_mirror | grep -E $regex_url` == "" ]]
do
  echo "请输入docker镜像加速地址:"
  read -p "docker镜像加速地址:" -r docker_mirror
done

# 准备文件
# mysql配置文件
mkdir -p /etc/mysql
tee /etc/mysql/my.cnf <<-'EOF'
[mysqld]
port=3306
max_connections=200
character-set-server=utf8mb4
default-storage-engine=INNODB
explicit_defaults_for_timestamp=true
default-time-zone='+08:00'
lower_case_table_names=1
group_concat_max_len=10240
skip-host-cache
skip-name-resolve
[mysqldump]
quick
quote-names
max_allowed_packet=16M
EOF

# nginx配置文件
mkdir -p /etc/nginx
tee /etc/nginx/nginx.conf <<-'EOF'
user root;

worker_processes  1;

error_log  /var/log/nginx/error.log  info;

worker_rlimit_nofile 1024;

events {
    worker_connections  1024;
}

http {
    proxy_http_version 1.1;
    proxy_connect_timeout 600;
    proxy_read_timeout 600;
    proxy_send_timeout 600;
    client_max_body_size 50m;
    include       mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    keepalive_timeout  65;

    gzip on;
    sendfile on;
    gzip_buffers 16 8k;
    gzip_comp_level 2;
    gzip_http_version 1.1;
    gzip_min_length 256;
    gzip_proxied any;
    gzip_vary on;

    gzip_types
        text/xml application/xml application/atom+xml application/rss+xml application/xhtml+xml image/svg+xml
        text/javascript application/javascript application/x-javascript
        text/x-json application/json application/x-web-app-manifest+json
        text/css text/plain text/x-component
        font/opentype font/ttf application/x-font-ttf application/vnd.ms-fontobject
        image/x-icon image/jpeg image/gif image/png;

    server {
        listen 80;
        server_name demofile;
        location ^~/demofile/ {
            add_header 'Access-Control-Allow-Origin' '*';
            add_header 'Access-Control-Allow-Credentials' 'true';
            alias /home/demofile/;
        }
    }

}
EOF

# 容器脚本
# nginx
tee nginx.sh <<-'EOF'
#!/bin/bash
docker run -d --name nginx \
--restart=always \
-v /etc/nginx/nginx.conf:/etc/nginx/nginx.conf \
-v /var/log/nginx:/var/log/nginx \
-v /home/demofile:/home/demofile \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 80:80 \
nginx:1.17-alpine
EOF

# redis
tee redis.sh <<-'EOF'
#!/bin/bash
docker run -d --name redis \
--restart=always \
-v /opt/redis:/data \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 6379:6379 \
redis:5-alpine \
redis-server --appendonly yes \
--requirepass demo
EOF

# zookeeper
tee zookeeper.sh <<-'EOF'
#!/bin/bash
docker run -d --name zookeeper \
--restart=always \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 2181:2181 \
-t \
wurstmeister/zookeeper
EOF

# kafka
tee kafka.sh <<-'EOF'
#!/bin/bash
docker run  -d --name kafka \
--restart=always \
--add-host docker:local_ip \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 9092:9092 \
-e KAFKA_BROKER_ID=0 \
-e KAFKA_ZOOKEEPER_CONNECT=docker:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://docker:9092 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
-e KAFKA_JVM_PERFORMANCE_OPTS=' -Xmx512m -Xms512m' \
-t \
wurstmeister/kafka:2.11-2.0.1
EOF
# 替换为真实ip
sed -i "s#--add-host docker:local_ip#--add-host docker:$local_ip#g" kafka.sh

# mysql
tee mysql.sh <<-'EOF'
#!/bin/bash
docker run -d --name mysql \
--restart=always \
-e MYSQL_ROOT_PASSWORD=root \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-v /etc/mysql:/etc/mysql/conf.d \
-p 3306:3306 \
mysql:5.7.26
EOF

# 安装docker
# 安装必要的一些系统工具
yum install -y yum-utils device-mapper-persistent-data lvm2
# 添加软件源信息
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
# 本地yum缓存
yum makecache fast
# 安装docker-ce
yum -y install docker-ce
# 开启docker服务
systemctl start docker
# docker开机自启动
systemctl enable docker

# 配置docker镜像加速
mkdir -p /etc/docker
tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["docker_mirror"]
}
EOF
# 替换为真实镜像地址
sed -i "s#docker_mirror#$docker_mirror#g" /etc/docker/daemon.json

# 暴露2375端口
sed -i \
's#ExecStart=/usr/bin/dockerd -H fd://#ExecStart=/usr/bin/dockerd -H fd:// -H tcp://0.0.0.0:2375#g' \
/usr/lib/systemd/system/docker.service

# 重启docker服务
systemctl daemon-reload
systemctl restart docker

sleep 5

# 设置时区
rm -rf /etc/timezone
echo "Asia/Shanghai" > /etc/timezone

# 下载镜像，创建并启动容器
docker pull nginx:1.17-alpine
sh nginx.sh
docker pull redis:5-alpine
sh redis.sh
docker pull wurstmeister/zookeeper
sh zookeeper.sh
docker pull wurstmeister/kafka:2.11-2.0.1
sh kafka.sh
docker pull mysql:5.7.26
sh mysql.sh
docker pull anapsix/alpine-java:8_server-jre_unlimited
