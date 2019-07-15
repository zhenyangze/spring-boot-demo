#/bin/bash
docker run -d --name mysql \
-e MYSQL_ROOT_PASSWORD=root \
-v /etc/localtime:/etc/localtime \
-v /etc/mysql:/etc/mysql/conf.d \
-p 3306:3306 \
mysql:5.7.26
