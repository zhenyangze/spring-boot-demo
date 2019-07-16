FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER xuelingkang@163.com

ADD spring-boot-demo.jar app.jar

EXPOSE 8080

CMD nohup java -Dfile.encoding=utf-8 -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar app.jar > /root/spring-boot-demo/app.log
