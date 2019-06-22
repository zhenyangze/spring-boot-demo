FROM anapsix/alpine-java:8_server-jre_unlimited
ADD spring-boot-demo.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
