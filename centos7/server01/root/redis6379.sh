#!/bin/bash
docker run -d --name redis6379 \
--restart=always \
-v /mnt/redis/6379:/data \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
--network host \
redis:alpine \
redis-server --appendonly yes \
--port 6379 \
--requirepass demo \
--masterauth demo
