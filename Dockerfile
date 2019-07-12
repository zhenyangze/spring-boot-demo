FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER xuelingkang@163.com

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /spring-boot-demo

ADD spring-boot-demo.jar app.jar

EXPOSE 8080

CMD nohup java -Dfile.encoding=utf-8 -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar app.jar > /var/log/spring-boot-demo.log
