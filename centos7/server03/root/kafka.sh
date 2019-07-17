#/bin/bash
docker run -d --name kafka \
--publish 9092:9092 \
--link zookeeper \
--env KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
--env KAFKA_ADVERTISED_HOST_NAME=172.26.245.49 \
--env KAFKA_ADVERTISED_PORT=9092 \
--env KAFKA_JVM_PERFORMANCE_OPTS=' -Xmx512m -Xms512m' \
--volume /etc/localtime:/etc/localtime \
--volume /etc/timezone:/etc/timezone \
wurstmeister/kafka:2.11-2.0.1

