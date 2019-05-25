#/bin/bash
dir=/root/spring-boot-demo
jar=${dir}/spring-boot-demo.jar
log=${dir}/spring-boot-demo.log

process=`ps -ef|grep -E "${jar}"|grep -v grep|grep -v PPID|awk '{ print $2}'`

echo "关闭应用"
echo ${process}
for i in ${process}
do
    echo "Kill process[ ${i} ]"
    kill -9 ${i}
done
rm -rf ${log}
echo "关闭完成"
