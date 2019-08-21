#!/bin/bash
sysdate=$(date +"%Y-%m-%d")

su demofile << EOF
cd /home/demofile
zip -r demofile_${sysdate}.zip ./
EOF

mkdir -p /opt/demofile
cd /opt/demofile
mv /home/demofile/demofile_${sysdate}.zip ./

echo "----------${sysdate}----------"
echo "附件备份成功"
