#/bin/bash
docker run -d --name zookeeper \
--restart=always \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 2181:2181 \
-t \
wurstmeister/zookeeper
