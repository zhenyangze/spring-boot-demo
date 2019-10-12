#!/bin/bash
docker run -d --name sentinel26381 \
--restart=always \
-v /etc/sentinel/26381/sentinel.conf:/conf/sentinel.conf \
-v /var/log/sentinel:/var/log/sentinel \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
--network host \
redis:5-alpine \
redis-sentinel \
/conf/sentinel.conf --sentinel
