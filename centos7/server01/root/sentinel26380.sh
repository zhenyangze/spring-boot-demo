#/bin/bash
docker run -d --name sentinel26380 \
-v /etc/sentinel/26380/sentinel.conf:/conf/sentinel.conf \
-v /var/log/sentinel:/var/log/sentinel \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
--network host \
redis:alpine \
redis-sentinel \
/conf/sentinel.conf --sentinel
