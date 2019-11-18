#!/bin/bash
docker run -d --name rabbitmq \
--restart=always \
--hostname rabbitmq \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-p 5672:5672 \
-p 15672:15672 \
-e RABBITMQ_DEFAULT_USER=demo \
-e RABBITMQ_DEFAULT_PASS=demo \
rabbitmq:3.7-management-alpine
