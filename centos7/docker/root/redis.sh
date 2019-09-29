#!/bin/bash
docker run -d --name redis \
--restart=always \
-v /opt/redis:/data \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 6379:6379 \
redis:alpine \
redis-server --appendonly yes \
--requirepass demo
