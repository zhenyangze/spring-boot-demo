#!/bin/bash
docker run -d --name redis \
--restart=always \
-v /mnt/redis/6379:/data \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 6379:6379 \
redis:5-alpine \
redis-server --appendonly yes \
--port 6379 \
--requirepass demo
