#/bin/bash
docker run -d --name kafka \
--publish 9092:9092 \
--link zookeeper \
--add-host server02:172.26.245.48 \
--env KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
--env KAFKA_ADVERTISED_HOST_NAME=server02 \
--env KAFKA_ADVERTISED_PORT=9092 \
--env KAFKA_JVM_PERFORMANCE_OPTS=' -Xmx256m -Xms256m' \
--volume /etc/localtime:/etc/localtime \
wurstmeister/kafka:2.11-2.0.1
