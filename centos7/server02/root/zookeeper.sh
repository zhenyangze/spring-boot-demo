#/bin/bash
docker run -d --name zookeeper \
-v /etc/localtime:/etc/localtime \
-p 2181 \
-t \
wurstmeister/zookeeper
