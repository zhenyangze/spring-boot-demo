#/bin/bash
docker run -d --name sentinel26379 \
-v /etc/sentinel/26379/sentinel.conf:/conf/sentinel.conf \
-v /var/log/sentinel:/var/log/sentinel \
-v /etc/localtime:/etc/localtime \
--network host \
redis:alpine \
redis-sentinel \
/conf/sentinel.conf --sentinel
