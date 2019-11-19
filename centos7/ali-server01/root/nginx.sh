#!/bin/bash
docker run -d --name nginx \
--link bootdemo \
--restart=always \
-v /etc/nginx/nginx.conf:/etc/nginx/nginx.conf \
-v /etc/nginx/cert:/etc/nginx/cert \
-v /var/log/nginx:/var/log/nginx \
-v /root/react-demo:/root/react-demo \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 80:80 \
-p 443:443 \
--add-host ali-server02:172.26.245.48 \
nginx:1.17-alpine
