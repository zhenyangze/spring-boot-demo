#!/bin/bash
ik=/root/elasticsearch-analysis-ik-6.4.3.zip
if [ -f "$ik" ]; then
  mkdir -p /etc/elasticsearch/plugins/ik
  unzip -o -d /etc/elasticsearch/plugins/ik /root/elasticsearch-analysis-ik-6.4.3.zip
  rm -rf $ik
fi
docker run -d --name elasticsearch \
--restart=always \
-e ES_JAVA_OPTS="-Xms256m -Xmx256m" \
-e "discovery.type=single-node" \
-v /etc/localtime:/etc/localtime \
-v /etc/timezone:/etc/timezone \
-v /etc/elasticsearch/plugins/ik:/usr/share/elasticsearch/plugins/ik \
-p 9200:9200 \
-p 9300:9300 \
elasticsearch:6.4.3
