#!/bin/bash
docker run -d --name mysql \
--restart=always \
-e MYSQL_ROOT_PASSWORD=root \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-v /etc/mysql:/etc/mysql/conf.d \
-p 3306:3306 \
mysql:5.7.26
