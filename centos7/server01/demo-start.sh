#/bin/bash
dir=/root/spring-boot-demo
jar=${dir}/spring-boot-demo.jar
log=${dir}/spring-boot-demo.log
nohup java -Dfile.encoding=utf-8 -jar ${jar} --spring.mail.username=xuelingkang@163.com --spring.mail.password=163sqm > ${log} 2>&1 &
