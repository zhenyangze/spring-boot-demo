#!/bin/bash
docker run  -d --name kafka \
--restart=always \
--add-host docker:192.168.72.211 \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 9092:9092 \
-e KAFKA_BROKER_ID=0 \
-e KAFKA_ZOOKEEPER_CONNECT=docker:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://docker:9092 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
-e KAFKA_JVM_PERFORMANCE_OPTS=' -Xmx512m -Xms512m' \
-t \
wurstmeister/kafka:2.11-2.0.1
