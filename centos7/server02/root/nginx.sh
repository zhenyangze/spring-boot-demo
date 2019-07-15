#/bin/bash
docker run -d --name nginx \
-v /etc/nginx/nginx.conf:/etc/nginx/nginx.conf \
-v /var/log/nginx:/var/log/nginx \
-v /home/demofile:/home/demofile \
-v /etc/localtime:/etc/localtime \
-p 80:80 \
nginx:alpine
