#/bin/bash
docker run -d --name redis6379 \
-v /mnt/redis/6379:/data \
-v /etc/localtime:/etc/localtime \
--network host \
redis:alpine \
redis-server --appendonly yes \
--port 6379 \
--requirepass demo \
--masterauth demo
