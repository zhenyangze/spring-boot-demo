#!/bin/bash
sysdate=$(date +"%Y-%m-%d")
docker exec -i mysql mysqldump -uroot -proot demo > /opt/mysql/demo_backup.sql
gzip -c /opt/mysql/demo_backup.sql > /opt/mysql/demo_backup_${sysdate}.sql.gz
rm -rf /opt/mysql/demo_backup.sql
echo "----------${sysdate}----------"
echo "demo数据库备份成功"
