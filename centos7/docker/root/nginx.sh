#!/bin/bash
docker run -d --name nginx \
--restart=always \
-v /etc/nginx/nginx.conf:/etc/nginx/nginx.conf \
-v /var/log/nginx:/var/log/nginx \
-v /home/demofile:/home/demofile \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 80:80 \
nginx:alpine
