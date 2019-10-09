# spring-boot-demo

## 介绍

spring boot项目模板

运行环境：centos7 docker

前端react项目地址：<a href="https://gitee.com/xuelingkang/react-demo" target="_blank">react-demo</a>

<a href="https://blog.csdn.net/qq_35433926" target="_blank">博客主页</a>

### 持续提取模块中

| 模块名称 | 码云 | github | maven坐标 |
| :---: | :---: | :---: | :---: |
| websocket | <a href="https://gitee.com/xuelingkang/websocket" target="_blank">码云</a> | <a href="https://github.com/xuelingkang/websocket" target="_blank">github</a> | `<groupId>com.xzixi</groupId>`<br>`<artifactId>interceptable-websocket</artifactId>`<br>`<version>1.0</version>` |
| zookeeper | <a href="https://gitee.com/xuelingkang/zookeeper" target="_blank">码云</a> | <a href="https://github.com/xuelingkang/zookeeper" target="_blank">github</a> | `<groupId>com.xzixi</groupId>`<br>`<artifactId>curator-client-spring-boot-starter</artifactId>`<br>`<version>1.0</version>` |
| sftp | <a href="https://gitee.com/xuelingkang/sftp" target="_blank">码云</a> | <a href="https://github.com/xuelingkang/sftp" target="_blank">github</a> | `<groupId>com.xzixi</groupId>`<br>`<artifactId>sftp-pool-spring-boot-starter</artifactId>`<br>`<version>1.0</version>` |
| swagger2 | <a href="https://gitee.com/xuelingkang/swagger2" target="_blank">码云</a> | <a href="https://github.com/xuelingkang/swagger2" target="_blank">github</a> | `<groupId>com.xzixi</groupId>`<br>`<artifactId>swagger2-plus</artifactId>`<br>`<version>1.0</version>` |

## 部署步骤

### 开发环境

1. 准备一个linux服务器（用VMware安装虚拟机或本地其他机器）用来安装其他服务
2. 修改`C:\Windows\System32\drivers\etc\hosts`文件，将linux的ip映射到docker
3. 安装docker-ce，并配置镜像加速，参考阿里云容器镜像服务
4. 将工程目录下的`centos7/docker`目录中的文件修改为***UNIX格式***，按照目录结构拷贝到linux虚拟机对应目录下，***注意提前修改文件格式为UNIX，否则无法运行***
5. 修改`kafka.sh`的第四行，将ip替换为linux的ip
6. 按顺序启动以下容器
  - `nginx.sh`
  - `redis.sh`
  - `zookeeper.sh`
  - `kafka.sh`
  - `mysql.sh`
7. 创建`demo`数据库，将工程目录下`/src/main/resources/schema/demo.sql`导入`demo`数据库
8. 修改`application-dev.yml`，将`spring.mail.username`和`spring.mail.password`修改为自己的邮箱账号和授权码
9. 启动工程

### 生产环境

1. 本地新建三个1核1G的centos7虚拟机，或者直接在阿里云创建
2. 安装docker-ce，配置镜像加速，参考阿里云容器镜像服务
3. 修改/etc/hosts，增加如下映射，***ip改成自己的局域网ip，三个虚拟机都需要增加这三行***

```bash
172.26.245.47   ali-server01    server01
172.26.245.48   ali-server02    server02
172.26.245.49   ali-server03    server03
```

4. 将工程中centos7目录下的文件修改为***UNIX格式***，按照目录结构分别拷贝到三个虚拟机的对应目录下，***注意提前修改文件格式为UNIX，否则无法运行***
5. 修改/root目录下的shell脚本，将`--add-host`参数对应的真实ip修改为自己虚拟机的ip
6. 构建应用镜像，<a href="https://blog.csdn.net/qq_35433926/article/details/95969980" target="_blank">参考博客</a>
7. 在server01的/root下新建bootdemo.sh脚本，内容如下，***注意修改ip和邮箱账号授权码及镜像版本号等变量***

```bash
#/bin/bash
docker run -d --name bootdemo \
--restart=always \
-e JAVA_OPTS='-Dspring.profiles.active=prod -Dspring.mail.username=xxxxxx@163.com -Dspring.mail.password=xxxxxx' \
-v /var/log/spring-boot-demo:/root/spring-boot-demo \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
--add-host ali-server01:172.26.245.47 \
--add-host ali-server02:172.26.245.48 \
--add-host ali-server03:172.26.245.49 \
bootdemo:1.0.7
```

8. 按照下表顺序启动容器

| 服务器 | 启动脚本 |
| :---: | :---: |
| server03 | `zookeeper.sh` |
| server03 | `kafka.sh` |
| server02 | `mysql.sh`<br>**mysql启动完成后，创建`demo`数据库，将工程目录下<br>`/src/main/resources/schema/demo.sql`导入`demo`数据库** |
| server02 | `nginx.sh` |
| server01 | `redis6379.sh` |
| server01 | `redis6380.sh` |
| server01 | `redis6381.sh` |
| server01 | `sentinel26379.sh` |
| server01 | `sentinel26380.sh` |
| server01 | `sentinel26381.sh` |
| server01 | `bootdemo.sh` |
| server01 | `nginx.sh` |

9. <a href="https://gitee.com/xuelingkang/react-demo" target="_blank">前端项目部署</a>

## 基本功能

* 基础框架：spring-boot-2.1.3.RELEASE
>优点：敏捷开发，扩展方便

* web容器：jetty
>优点：轻量级<br>
应用：配置了access日志

* 数据源：druid
>优点：速度快，监控<br>
应用：配置了监控服务

* orm框架：mybatis-plus
>优点：减少重复编码量<br>
应用：继承并扩展了IBaseMapper IService ServiceImpl，增加泛型边界，增加了类似oracle的merge方法，并统一常用方法命名

* 缓存：redis
>优点：减少数据库访问量，提高响应速度<br>
应用：换用用户token和方法返回值

* 日志：logback
>优点：比log4j占用内存小且性能强<br>
应用：配置了日志分级别分包输出

* 定时任务：quartz
>优点：集群定时任务解决方案<br>
应用：预定义任务模板，自定义定时任务参数

* 认证授权框架：spring-security jwt websocket-security
>应用：实现前后分离请求认证，资源模块将http请求和websocket请求按照method和url保存起来，当用户发起请求时，通过授权决策器实现动态授权

* websocket
>应用：使用stomp子协议，继承并扩展了StompSubProtocolHandler，增加了对拦截器的支持，实现对SUBSCRIBE请求的动态授权，实现了群发消息和点对点消息<br>
这部分单独抽出了个模块<a href="https://gitee.com/xuelingkang/websocket" target="_blank">websocket</a>

* 邮件：javax.mail
>应用：实现了群发和附件

* 参数校验：hibernate validation
>应用：支持@Validate（自定义分组校验）和@Valid（嵌套校验对象中的属性）注解，在全局异常处理中捕获校验异常，并封装返回值

* restful文档：swagger2
>优点：只需要配置注解即可动态生成文档<br>
应用：继承并扩展了ModelAttributeParameterExpander，增加了@IgnoreSwaggerParameter注解，用来隐藏不需要递归展开的属性

* swagger过滤器
>将spring-security的登录和登出url添加到文档中，
由于swagger通过springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration的swagger2ControllerMapping方法将/v2/api-docs请求映射到Swagger2Controller<br>
![Swagger2DocumetationConfiguration源码片段](https://images.gitee.com/uploads/images/2019/0724/154915_eb28248c_1672679.png "Swagger2DocumetationConfiguration.png")<br>
Swagger2Controller不是spring容器中的bean，无法使用aop和拦截器，所以在过滤器中拦截了/v2/api-docs请求，将登录和登出动态添加到返回值中

## 其他配置

* 使用kafka消息队列发送websocket消息
>支持服务集群部署

* 线程池和异步任务支持
>支持使用@Async定义异步任务

* fastjson
>比jackson速度快，统一配置了返回值格式，可以在字段上使用@JsonField进行个性化配置

* 全局跨域支持
>com.xzixi.demo.config.GlobalCorsConfig

* sftp
>实现了sftp连接池，sftp上传下载等功能

## 欢迎issue和star！
