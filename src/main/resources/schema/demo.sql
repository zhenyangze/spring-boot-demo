/*
Navicat MySQL Data Transfer

Source Server         : server02
Source Server Version : 50726
Source Host           : server02:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-05-26 00:04:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `CALENDAR_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `CRON_EXPRESSION` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `INSTANCE_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(190) COLLATE utf8mb4_bin DEFAULT NULL,
  `JOB_GROUP` varchar(190) COLLATE utf8mb4_bin DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `JOB_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8mb4_bin NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8mb4_bin NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `INSTANCE_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('clusteredScheduler', 'server011558798435874', '1558800268761', '10000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `JOB_NAME` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `JOB_GROUP` varchar(190) COLLATE utf8mb4_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8mb4_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) COLLATE utf8mb4_bin DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for t_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attachment_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '附件名称',
  `attachment_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '访问地址',
  `attachment_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '本地路径',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_attachment
-- ----------------------------
INSERT INTO `t_attachment` VALUES ('119', 'liushihong.png', '/demofile/head/2019/5/1558794732206.png', '/home/demofile/head/2019/5/1558794732206.png', '1558794732242');
INSERT INTO `t_attachment` VALUES ('120', 'xuelingkang.png', '/demofile/head/2019/5/1558794820025.png', '/home/demofile/head/2019/5/1558794820025.png', '1558794820080');

-- ----------------------------
-- Table structure for t_broadcast_message
-- ----------------------------
DROP TABLE IF EXISTS `t_broadcast_message`;
CREATE TABLE `t_broadcast_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_time` bigint(20) DEFAULT NULL COMMENT '发送时间',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息内容',
  `send_user_id` int(11) DEFAULT NULL COMMENT '发送用户id',
  PRIMARY KEY (`id`),
  KEY `t_broadcast_message_fk_send_user_id` (`send_user_id`),
  CONSTRAINT `t_broadcast_message_fk_send_user_id` FOREIGN KEY (`send_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_broadcast_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_chat_message
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_message`;
CREATE TABLE `t_chat_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_time` bigint(20) DEFAULT NULL COMMENT '发送时间',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消息内容',
  `send_user_id` int(11) DEFAULT NULL COMMENT '发送用户id',
  `to_user_id` int(11) DEFAULT NULL COMMENT '接收用户id',
  `read_status` tinyint(1) DEFAULT NULL COMMENT '阅读状态，1已读，0未读',
  PRIMARY KEY (`id`),
  KEY `t_single_message_fk_send_user_id` (`send_user_id`),
  KEY `t_single_message_fk_to_user_id` (`to_user_id`),
  CONSTRAINT `t_single_message_fk_send_user_id` FOREIGN KEY (`send_user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_single_message_fk_to_user_id` FOREIGN KEY (`to_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_chat_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '部门名称',
  `level` int(2) DEFAULT NULL COMMENT '部门等级',
  `pid` int(11) DEFAULT NULL COMMENT '上级部门id',
  `seq` int(5) DEFAULT NULL COMMENT '顺序',
  `full_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '全名',
  PRIMARY KEY (`id`),
  KEY `t_dept_fk_pid` (`pid`),
  KEY `t_dept_idx_seq` (`seq`),
  CONSTRAINT `t_dept_fk_pid` FOREIGN KEY (`pid`) REFERENCES `t_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '公司', '1', null, '100', '公司');
INSERT INTO `t_dept` VALUES ('2', '人资行政部', '2', '1', '100', '公司-人资行政部');
INSERT INTO `t_dept` VALUES ('3', '财务部', '2', '1', '200', '公司-财务部');
INSERT INTO `t_dept` VALUES ('4', '市场部', '2', '1', '300', '公司-市场部');
INSERT INTO `t_dept` VALUES ('5', '技术部', '2', '1', '500', '公司-技术部');
INSERT INTO `t_dept` VALUES ('6', '人事部', '3', '2', '100', '公司-人资行政部-人事部');
INSERT INTO `t_dept` VALUES ('7', '行政部', '3', '2', '200', '公司-人资行政部-行政部');
INSERT INTO `t_dept` VALUES ('8', '产品部', '3', '5', '100', '公司-技术部-产品部');
INSERT INTO `t_dept` VALUES ('9', '开发部', '3', '5', '200', '公司-技术部-开发部');
INSERT INTO `t_dept` VALUES ('10', '运维部', '3', '5', '300', '公司-技术部-运维部');
INSERT INTO `t_dept` VALUES ('11', '测试部', '3', '5', '400', '公司-技术部-测试部');
INSERT INTO `t_dept` VALUES ('12', '销售部', '3', '4', '100', '公司-市场部-销售部');
INSERT INTO `t_dept` VALUES ('13', '售后部', '3', '4', '200', '公司-市场部-售后部');
INSERT INTO `t_dept` VALUES ('14', '客服部', '2', '1', '400', '公司-客服部');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务描述',
  `start_time` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `end_time` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `cron_expression` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'cron表达式',
  `job_template_id` int(11) DEFAULT NULL COMMENT '任务模板id',
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '调度器名称',
  `job_name` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务组',
  `trigger_name` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '触发器名称',
  `trigger_group` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '触发器组',
  PRIMARY KEY (`id`),
  KEY `t_job_fk_job_template_id` (`job_template_id`),
  CONSTRAINT `t_job_fk_job_template_id` FOREIGN KEY (`job_template_id`) REFERENCES `t_job_template` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_job
-- ----------------------------

-- ----------------------------
-- Table structure for t_job_parameter
-- ----------------------------
DROP TABLE IF EXISTS `t_job_parameter`;
CREATE TABLE `t_job_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parameter_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数名称',
  `parameter_value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数值',
  `job_id` int(11) DEFAULT NULL COMMENT '定时任务id',
  PRIMARY KEY (`id`),
  KEY `t_job_parameter_fk_job_id` (`job_id`),
  CONSTRAINT `t_job_parameter_fk_job_id` FOREIGN KEY (`job_id`) REFERENCES `t_job` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_job_parameter
-- ----------------------------

-- ----------------------------
-- Table structure for t_job_template
-- ----------------------------
DROP TABLE IF EXISTS `t_job_template`;
CREATE TABLE `t_job_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务名称',
  `job_class_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'org.quartz.Job的实现类或org.springframework.scheduling.quartz.QuartzJobBean的子类',
  `job_desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_job_template
-- ----------------------------
INSERT INTO `t_job_template` VALUES ('2', '测试', 'com.example.job.TestJob', '测试更新定时任务模板');
INSERT INTO `t_job_template` VALUES ('7', '打印指定用户信息', 'com.example.job.LogUserJob', '打印指定用户信息');

-- ----------------------------
-- Table structure for t_job_template_parameter
-- ----------------------------
DROP TABLE IF EXISTS `t_job_template_parameter`;
CREATE TABLE `t_job_template_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parameter_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数名称',
  `parameter_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数类型',
  `parameter_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数描述',
  `job_template_id` int(11) DEFAULT NULL COMMENT '任务模板id',
  PRIMARY KEY (`id`),
  KEY `t_job_template_parameter_fk_job_template_id` (`job_template_id`),
  CONSTRAINT `t_job_template_parameter_fk_job_template_id` FOREIGN KEY (`job_template_id`) REFERENCES `t_job_template` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_job_template_parameter
-- ----------------------------
INSERT INTO `t_job_template_parameter` VALUES ('3', 'name', 'string', '字符串参数', '2');
INSERT INTO `t_job_template_parameter` VALUES ('4', 'age', 'int', '整数参数', '2');
INSERT INTO `t_job_template_parameter` VALUES ('5', 'weight', 'float', '浮点参数', '2');
INSERT INTO `t_job_template_parameter` VALUES ('7', 'username', 'string', '用户名', '7');

-- ----------------------------
-- Table structure for t_mail
-- ----------------------------
DROP TABLE IF EXISTS `t_mail`;
CREATE TABLE `t_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail_subject` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮件标题',
  `mail_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮件类型',
  `mail_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮件状态',
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `send_time` bigint(20) DEFAULT NULL COMMENT '发送时间',
  `send_user_id` int(11) DEFAULT NULL COMMENT '发送用户id',
  PRIMARY KEY (`id`),
  KEY `t_mail_fk_send_user_id` (`send_user_id`),
  CONSTRAINT `t_mail_fk_send_user_id` FOREIGN KEY (`send_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_mail
-- ----------------------------

-- ----------------------------
-- Table structure for t_mail_attachment_link
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_attachment_link`;
CREATE TABLE `t_mail_attachment_link` (
  `mail_id` int(11) NOT NULL,
  `attachment_id` int(11) NOT NULL,
  PRIMARY KEY (`mail_id`,`attachment_id`),
  KEY `t_mail_attachment_link_fk_attachment_id` (`attachment_id`),
  CONSTRAINT `t_mail_attachment_link_fk_attachment_id` FOREIGN KEY (`attachment_id`) REFERENCES `t_attachment` (`id`),
  CONSTRAINT `t_mail_attachment_link_fk_mail_id` FOREIGN KEY (`mail_id`) REFERENCES `t_mail` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_mail_attachment_link
-- ----------------------------

-- ----------------------------
-- Table structure for t_mail_content
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_content`;
CREATE TABLE `t_mail_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '邮件内容',
  `mail_id` int(11) DEFAULT NULL COMMENT '邮件id',
  PRIMARY KEY (`id`),
  KEY `t_mail_content_fk_mail_id` (`mail_id`),
  CONSTRAINT `t_mail_content_fk_mail_id` FOREIGN KEY (`mail_id`) REFERENCES `t_mail` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_mail_content
-- ----------------------------

-- ----------------------------
-- Table structure for t_mail_to_user_link
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_to_user_link`;
CREATE TABLE `t_mail_to_user_link` (
  `mail_id` int(11) NOT NULL COMMENT '邮件id',
  `to_user_id` int(11) NOT NULL COMMENT '接收用户id',
  PRIMARY KEY (`mail_id`,`to_user_id`),
  KEY `t_mail_to_user_link_fk_to_user_id` (`to_user_id`),
  CONSTRAINT `t_mail_to_user_link_fk_mail_id` FOREIGN KEY (`mail_id`) REFERENCES `t_mail` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_mail_to_user_link_fk_to_user_id` FOREIGN KEY (`to_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_mail_to_user_link
-- ----------------------------

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '协议类型',
  `resource_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源类别',
  `resource_seq` int(5) DEFAULT NULL COMMENT '资源顺序',
  `resource_pattern` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源pattern',
  `resource_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '请求方法',
  `resource_desc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', 'http', '附件', '1300', '/attachment/*/*', 'GET', '附件列表');
INSERT INTO `t_resource` VALUES ('2', 'http', '附件', '1400', '/attachment/*', 'GET', '附件详情');
INSERT INTO `t_resource` VALUES ('3', 'http', '附件', '1500', '/attachment/*', 'DELETE', '删除附件');
INSERT INTO `t_resource` VALUES ('4', 'http', '部门', '700', '/dept/*/*', 'GET', '部门列表');
INSERT INTO `t_resource` VALUES ('5', 'http', '部门', '800', '/dept/*', 'GET', '部门详情');
INSERT INTO `t_resource` VALUES ('6', 'http', '部门', '900', '/dept', 'POST', '保存部门');
INSERT INTO `t_resource` VALUES ('7', 'http', '部门', '1000', '/dept', 'PUT', '更新部门');
INSERT INTO `t_resource` VALUES ('8', 'http', '部门', '1100', '/dept/*', 'DELETE', '删除部门');
INSERT INTO `t_resource` VALUES ('9', 'http', '附件', '1200', '/file/*/*', 'POST', '上传文件');
INSERT INTO `t_resource` VALUES ('10', 'http', '权限', '2600', '/resource/*/*', 'GET', '权限列表');
INSERT INTO `t_resource` VALUES ('11', 'http', '权限', '2700', '/resource/*', 'GET', '权限详情');
INSERT INTO `t_resource` VALUES ('12', 'http', '权限', '2800', '/resource', 'POST', '保存权限');
INSERT INTO `t_resource` VALUES ('13', 'http', '权限', '2900', '/resource', 'PUT', '更新权限');
INSERT INTO `t_resource` VALUES ('14', 'http', '权限', '3000', '/resource/*', 'DELETE', '删除权限');
INSERT INTO `t_resource` VALUES ('15', 'http', '角色', '2100', '/role/*/*', 'GET', '角色列表');
INSERT INTO `t_resource` VALUES ('16', 'http', '角色', '2200', '/role/*', 'GET', '角色详情');
INSERT INTO `t_resource` VALUES ('17', 'http', '角色', '2300', '/role', 'POST', '保存角色');
INSERT INTO `t_resource` VALUES ('18', 'http', '角色', '2400', '/role', 'PUT', '更新角色');
INSERT INTO `t_resource` VALUES ('19', 'http', '角色', '2500', '/role/*', 'DELETE', '删除角色');
INSERT INTO `t_resource` VALUES ('20', 'http', '用户', '1600', '/user/*/*', 'GET', '用户列表');
INSERT INTO `t_resource` VALUES ('21', 'http', '用户', '1700', '/user/*', 'GET', '用户详情');
INSERT INTO `t_resource` VALUES ('22', 'http', '用户', '1800', '/user', 'POST', '保存用户');
INSERT INTO `t_resource` VALUES ('23', 'http', '用户', '1900', '/user', 'PUT', '更新用户');
INSERT INTO `t_resource` VALUES ('24', 'http', '用户', '2000', '/user/*', 'DELETE', '删除用户');
INSERT INTO `t_resource` VALUES ('25', 'http', 'websocket', '3100', '/endpoint/**', null, '获取websocket信息及握手');
INSERT INTO `t_resource` VALUES ('26', 'websocket', 'websocket', '3200', '/topic/broadcast', 'SUBSCRIBE', '订阅广播消息');
INSERT INTO `t_resource` VALUES ('28', 'http', '广播', '3400', '/broadcast', 'POST', '发送广播消息');
INSERT INTO `t_resource` VALUES ('29', 'http', '广播', '3500', '/broadcast/*/*', 'GET', '广播列表');
INSERT INTO `t_resource` VALUES ('30', 'http', '广播', '3600', '/broadcast/*', 'GET', '广播详情');
INSERT INTO `t_resource` VALUES ('31', 'http', '消息', '3700', '/chat/*/*', 'GET', '消息列表');
INSERT INTO `t_resource` VALUES ('32', 'http', '消息', '3800', '/chat/*', 'GET', '消息详情');
INSERT INTO `t_resource` VALUES ('33', 'http', '消息', '3900', '/chat/*/*/*/*', 'GET', '往来消息');
INSERT INTO `t_resource` VALUES ('34', 'http', '消息', '4000', '/chat', 'PUT', '更新消息');
INSERT INTO `t_resource` VALUES ('35', 'http', '消息', '4100', '/chat', 'POST', '发送点对点消息');
INSERT INTO `t_resource` VALUES ('36', 'websocket', 'websocket', '3300', '/user/topic/chat', 'SUBSCRIBE', '订阅点对点消息');
INSERT INTO `t_resource` VALUES ('39', 'http', '定时任务模板', '4200', '/jobTemplate/*/*', 'GET', '任务模板列表');
INSERT INTO `t_resource` VALUES ('40', 'http', '定时任务模板', '4300', '/jobTemplate/*', 'GET', '任务模板详情');
INSERT INTO `t_resource` VALUES ('41', 'http', '定时任务模板', '4400', '/jobTemplate', 'POST', '保存任务模板');
INSERT INTO `t_resource` VALUES ('42', 'http', '定时任务模板', '4500', '/jobTemplate', 'PUT', '更新任务模板');
INSERT INTO `t_resource` VALUES ('43', 'http', '定时任务模板', '4600', '/jobTemplate/*', 'DELETE', '删除任务模板');
INSERT INTO `t_resource` VALUES ('44', 'http', '定时任务', '4700', '/job/*/*', 'GET', '定时任务列表');
INSERT INTO `t_resource` VALUES ('45', 'http', '定时任务', '4800', '/job/*', 'GET', '定时任务详情');
INSERT INTO `t_resource` VALUES ('46', 'http', '定时任务', '4900', '/job', 'POST', '保存定时任务');
INSERT INTO `t_resource` VALUES ('47', 'http', '定时任务', '5000', '/job', 'PUT', '更新定时任务');
INSERT INTO `t_resource` VALUES ('48', 'http', '定时任务', '5100', '/job/*', 'DELETE', '删除定时任务');
INSERT INTO `t_resource` VALUES ('49', 'http', '定时任务', '5200', '/job/pause/*', 'PATCH', '暂停定时任务');
INSERT INTO `t_resource` VALUES ('50', 'http', '定时任务', '5300', '/job/resume/*', 'PATCH', '恢复定时任务');
INSERT INTO `t_resource` VALUES ('51', 'http', '邮件', '5400', '/mail/*/*', 'GET', '邮件列表');
INSERT INTO `t_resource` VALUES ('52', 'http', '邮件', '5500', '/mail/*', 'GET', '邮件详情');
INSERT INTO `t_resource` VALUES ('53', 'http', '邮件', '5600', '/mail', 'POST', '保存邮件');
INSERT INTO `t_resource` VALUES ('54', 'http', '邮件', '5700', '/mail', 'PUT', '更新邮件');
INSERT INTO `t_resource` VALUES ('55', 'http', '邮件', '5800', '/mail/*', 'PATCH', '发送邮件');
INSERT INTO `t_resource` VALUES ('56', 'http', '邮件', '5900', '/mail/*', 'DELETE', '删除邮件');
INSERT INTO `t_resource` VALUES ('57', 'http', '个人信息', '300', '/userinfo', 'GET', '查询个人信息');
INSERT INTO `t_resource` VALUES ('59', 'http', '个人信息', '200', '/userinfo', 'PUT', '更新个人信息');
INSERT INTO `t_resource` VALUES ('60', 'http', '个人信息', '400', '/userinfo/retrieve_password_mail', 'POST', '发送找回密码邮件');
INSERT INTO `t_resource` VALUES ('61', 'http', '个人信息', '500', '/userinfo/retrieve_password', 'POST', '找回密码');
INSERT INTO `t_resource` VALUES ('62', 'http', '部门', '600', '/dept/all', 'GET', '查询所有部门');
INSERT INTO `t_resource` VALUES ('63', 'http', '个人信息', '100', '/userinfo', 'POST', '注册个人信息');
INSERT INTO `t_resource` VALUES ('65', 'http', '权限', '2550', '/resource/categorys', 'GET', '查询所有权限类别');
INSERT INTO `t_resource` VALUES ('66', 'http', '权限', '2570', '/resource/all', 'GET', '查询所有权限');
INSERT INTO `t_resource` VALUES ('67', 'http', '角色', '2050', '/role/all', 'GET', '查询所有角色');
INSERT INTO `t_resource` VALUES ('68', 'http', '用户', '1550', '/user/all', 'GET', '查询所有用户');
INSERT INTO `t_resource` VALUES ('69', 'http', '个人信息', '250', '/userinfo', 'PATCH', '修改密码');
INSERT INTO `t_resource` VALUES ('70', 'http', '部门', '550', '/dept/structure', 'GET', '查询组织架构');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色描述',
  `role_seq` int(5) DEFAULT NULL COMMENT '角色顺序',
  `is_default` tinyint(1) DEFAULT NULL COMMENT '是否新用户默认角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_role_idx_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('7', 'ROLE_GUEST', '游客', '100', '0');
INSERT INTO `t_role` VALUES ('8', 'ROLE_PERSONAL', '个人信息管理', '200', '1');
INSERT INTO `t_role` VALUES ('9', 'ROLE_BASE', '基础数据查询', '300', '1');
INSERT INTO `t_role` VALUES ('10', 'ROLE_SYSTEM', '系统管理', '600', '0');
INSERT INTO `t_role` VALUES ('15', 'ROLE_CHAT', 'websocket点对点聊天', '500', '1');
INSERT INTO `t_role` VALUES ('16', 'ROLE_SUBSCRIBE_BROADCAST', '订阅广播消息', '400', '1');
INSERT INTO `t_role` VALUES ('17', 'ROLE_BROADCAST', '发送广播消息', '700', '0');
INSERT INTO `t_role` VALUES ('18', 'ROLE_SCHEDULER', '定时任务管理', '800', '0');
INSERT INTO `t_role` VALUES ('19', 'ROLE_MAIL', '邮件管理', '900', '0');

-- ----------------------------
-- Table structure for t_role_resource_link
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource_link`;
CREATE TABLE `t_role_resource_link` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `t_role_resource_link_fk_resource_id` (`resource_id`),
  CONSTRAINT `t_role_resource_link_fk_resource_id` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_role_resource_link_fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role_resource_link
-- ----------------------------
INSERT INTO `t_role_resource_link` VALUES ('10', '1');
INSERT INTO `t_role_resource_link` VALUES ('10', '2');
INSERT INTO `t_role_resource_link` VALUES ('10', '3');
INSERT INTO `t_role_resource_link` VALUES ('10', '4');
INSERT INTO `t_role_resource_link` VALUES ('10', '5');
INSERT INTO `t_role_resource_link` VALUES ('10', '6');
INSERT INTO `t_role_resource_link` VALUES ('10', '7');
INSERT INTO `t_role_resource_link` VALUES ('10', '8');
INSERT INTO `t_role_resource_link` VALUES ('8', '9');
INSERT INTO `t_role_resource_link` VALUES ('10', '9');
INSERT INTO `t_role_resource_link` VALUES ('10', '10');
INSERT INTO `t_role_resource_link` VALUES ('10', '11');
INSERT INTO `t_role_resource_link` VALUES ('10', '12');
INSERT INTO `t_role_resource_link` VALUES ('10', '13');
INSERT INTO `t_role_resource_link` VALUES ('10', '14');
INSERT INTO `t_role_resource_link` VALUES ('10', '15');
INSERT INTO `t_role_resource_link` VALUES ('10', '16');
INSERT INTO `t_role_resource_link` VALUES ('10', '17');
INSERT INTO `t_role_resource_link` VALUES ('10', '18');
INSERT INTO `t_role_resource_link` VALUES ('10', '19');
INSERT INTO `t_role_resource_link` VALUES ('10', '20');
INSERT INTO `t_role_resource_link` VALUES ('10', '21');
INSERT INTO `t_role_resource_link` VALUES ('10', '22');
INSERT INTO `t_role_resource_link` VALUES ('10', '23');
INSERT INTO `t_role_resource_link` VALUES ('10', '24');
INSERT INTO `t_role_resource_link` VALUES ('15', '25');
INSERT INTO `t_role_resource_link` VALUES ('16', '25');
INSERT INTO `t_role_resource_link` VALUES ('17', '25');
INSERT INTO `t_role_resource_link` VALUES ('16', '26');
INSERT INTO `t_role_resource_link` VALUES ('17', '26');
INSERT INTO `t_role_resource_link` VALUES ('17', '28');
INSERT INTO `t_role_resource_link` VALUES ('16', '29');
INSERT INTO `t_role_resource_link` VALUES ('17', '29');
INSERT INTO `t_role_resource_link` VALUES ('16', '30');
INSERT INTO `t_role_resource_link` VALUES ('17', '30');
INSERT INTO `t_role_resource_link` VALUES ('15', '31');
INSERT INTO `t_role_resource_link` VALUES ('15', '32');
INSERT INTO `t_role_resource_link` VALUES ('15', '33');
INSERT INTO `t_role_resource_link` VALUES ('15', '34');
INSERT INTO `t_role_resource_link` VALUES ('15', '35');
INSERT INTO `t_role_resource_link` VALUES ('15', '36');
INSERT INTO `t_role_resource_link` VALUES ('18', '39');
INSERT INTO `t_role_resource_link` VALUES ('18', '40');
INSERT INTO `t_role_resource_link` VALUES ('18', '41');
INSERT INTO `t_role_resource_link` VALUES ('18', '42');
INSERT INTO `t_role_resource_link` VALUES ('18', '43');
INSERT INTO `t_role_resource_link` VALUES ('18', '44');
INSERT INTO `t_role_resource_link` VALUES ('18', '45');
INSERT INTO `t_role_resource_link` VALUES ('18', '46');
INSERT INTO `t_role_resource_link` VALUES ('18', '47');
INSERT INTO `t_role_resource_link` VALUES ('18', '48');
INSERT INTO `t_role_resource_link` VALUES ('18', '49');
INSERT INTO `t_role_resource_link` VALUES ('18', '50');
INSERT INTO `t_role_resource_link` VALUES ('19', '51');
INSERT INTO `t_role_resource_link` VALUES ('19', '52');
INSERT INTO `t_role_resource_link` VALUES ('19', '53');
INSERT INTO `t_role_resource_link` VALUES ('19', '54');
INSERT INTO `t_role_resource_link` VALUES ('19', '55');
INSERT INTO `t_role_resource_link` VALUES ('19', '56');
INSERT INTO `t_role_resource_link` VALUES ('8', '57');
INSERT INTO `t_role_resource_link` VALUES ('8', '59');
INSERT INTO `t_role_resource_link` VALUES ('7', '60');
INSERT INTO `t_role_resource_link` VALUES ('7', '61');
INSERT INTO `t_role_resource_link` VALUES ('9', '62');
INSERT INTO `t_role_resource_link` VALUES ('10', '62');
INSERT INTO `t_role_resource_link` VALUES ('7', '63');
INSERT INTO `t_role_resource_link` VALUES ('9', '65');
INSERT INTO `t_role_resource_link` VALUES ('10', '65');
INSERT INTO `t_role_resource_link` VALUES ('9', '66');
INSERT INTO `t_role_resource_link` VALUES ('10', '66');
INSERT INTO `t_role_resource_link` VALUES ('9', '67');
INSERT INTO `t_role_resource_link` VALUES ('10', '67');
INSERT INTO `t_role_resource_link` VALUES ('9', '68');
INSERT INTO `t_role_resource_link` VALUES ('10', '68');
INSERT INTO `t_role_resource_link` VALUES ('8', '69');
INSERT INTO `t_role_resource_link` VALUES ('9', '70');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `head_img_id` int(11) DEFAULT NULL COMMENT '头像图片id',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别',
  `birth` bigint(20) DEFAULT NULL COMMENT '生日',
  `logintime` bigint(20) DEFAULT NULL COMMENT '登陆时间',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_idx_username` (`username`),
  UNIQUE KEY `t_user_uk_email` (`email`),
  KEY `t_user_fk_dept_id` (`dept_id`),
  KEY `t_user_fk_head_img_id` (`head_img_id`),
  CONSTRAINT `t_user_fk_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`id`),
  CONSTRAINT `t_user_fk_head_img_id` FOREIGN KEY (`head_img_id`) REFERENCES `t_attachment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('9', 'admin', '$2a$10$bR0way2ADFQDIt.UYSX3lekMwhHVOSwGUDLq.lBy6G0NQR/.AKXm2', 'xuelingkang@163.com', '薛凌康', '120', '1', '650736000736', '1558794839432', '1');
INSERT INTO `t_user` VALUES ('10', 'chongqingkai', '$2a$10$Blxb8W82p94mzFT1N4Unl.2RYu0g53HWzmvCoHH1mu99UzlQZA0O.', '574290057@qq.com', '种庆凯', null, '1', '655833600710', '1558718739973', '5');
INSERT INTO `t_user` VALUES ('11', 'guoxiaogang', '$2a$10$AXiiXAG7zEAfzaXT2h8CDeBKCKuSMbM2uHo6EH8VQhxjRWz2V5Pea', 'guoxiaogang@163.com', '郭小纲', null, '1', '96134400243', '1558718666696', '2');
INSERT INTO `t_user` VALUES ('12', 'yuxiaoqian', '$2a$10$MGBsCWjabTTI9o.6mwd9/OxhESr0PtjHNRBBq7JWyV6.5R8emRLry', 'yuxiaoqian@163.com', '于小谦', null, '1', '-29577599471', '1558718672143', '3');
INSERT INTO `t_user` VALUES ('13', 'yuexiaopeng', '$2a$10$XJ25TolfSjyIxCClZK1ANuqw.Oq/GqxGYo8j3rr2eqUMWeZ/0VxI.', 'yuexiaopeng@163.com', '岳小鹏', null, '1', '482342400369', '1558718654519', '6');
INSERT INTO `t_user` VALUES ('14', 'sunxiaoyue', '$2a$10$SU5EqinymbFShb8doa3pse/0JTnCJZt9qKXPLak6JExvNO96LlowG', 'sunxiaoyue@163.com', '孙小越', null, '1', '308592000475', '1558718660769', '7');
INSERT INTO `t_user` VALUES ('15', 'maxiaoyun', '$2a$10$KM4dSfMQvI8KSGkuBVjEHON4kFYjkcJe/6rbP18EWiy8fiDz7tFhq', 'maxiaoyun@163.com', '马小云', null, '1', '-167558399126', '1558721433623', '3');
INSERT INTO `t_user` VALUES ('16', 'maxiaoteng', '$2a$10$KEazUyrdlf.MSDjv/k9/fuuJc8qnyKeRUgUSeC/F9igqrJHZXxHt.', 'maxiaoteng@163.com', '马小腾', null, '0', '57513600026', '1558721441882', '3');
INSERT INTO `t_user` VALUES ('17', 'liuxiaohua', '$2a$10$sggjHj5hHVouQtpR0VjdV.vxzjgbWbycMHRUUJ10mXYJTFyx.3.2y', 'liuxiaohua@163.com', '刘小华', null, '0', '-260783999845', '1558721578405', '14');
INSERT INTO `t_user` VALUES ('18', 'zhouxiaochi', '$2a$10$3vXxoERIR2A27Rh20AcWHuOo0tHKyPMy6Rlw6Ro5niaA0/gRU/p9K', 'zhouxiaochi@163.com', '周小驰', null, '1', '-237628799753', '1558721584458', '4');
INSERT INTO `t_user` VALUES ('19', 'guoxiaojing', '$2a$10$h2MV6IOLgfTqE0RiFoEgVOQlJOhLfYaNyPWEqLOpedKU2VvZQaO0C', 'guoxiaojing@163.com', '郭小靖', null, '1', '946662834347', '1558721596408', '12');
INSERT INTO `t_user` VALUES ('20', 'huangxiaorong', '$2a$10$U8A42wYTUKFFHs80KGjqQ.wIItDQW3MbesmyJiR.keIZrCym4jimi', 'huangxiaorong@163.com', '黄小蓉', null, '0', '981043200658', '1558721603054', '13');
INSERT INTO `t_user` VALUES ('21', 'hongxiaogong', '$2a$10$NMAFlE6sED9U5ZOjq2cbA.sIq2hCFO.Q4UHwDRGmo3t.twMxknSpq', 'hongxiaogong@163.com', '洪小公', null, '1', '580147200571', '1558721613340', '8');
INSERT INTO `t_user` VALUES ('22', 'xiaowantong', '$2a$10$pbspXqozbuX6MDdItR8zjuG5TicW9VVb7jasPjXtZiQWrl5Ti.VJK', 'xiaowantong@163.com', '小顽童', null, '1', '932227200934', '1558721620315', '9');
INSERT INTO `t_user` VALUES ('23', 'yangxiaoguo', '$2a$10$vXSKsoAI3ocq66L7F75wUO4fswUh9t/KZgjgOZvaZRXNP4S9KyeA2', 'yangxiaoguo@163.com', '杨小过', null, '1', '795283200069', '1558721631988', '10');
INSERT INTO `t_user` VALUES ('24', 'baozupo', '$2a$10$TeH8lU5d/wrXf.LMv2MNUukIHZrOpPLCSh.J4rYzZT5YIGT03Lyni', 'baozupo@163.com', '包租婆', null, '0', '90086400957', '1558721639088', '11');
INSERT INTO `t_user` VALUES ('25', 'liushihong', '$2a$10$b7t95iUmZP4CVu1b6j9bQePNX2W8roqLcgtEbeopmQhz6FhZTFeW6', '952649292@qq.com', '刘世红', '119', '0', '689529600315', '1558794382760', '1');

-- ----------------------------
-- Table structure for t_user_role_link
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_link`;
CREATE TABLE `t_user_role_link` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `t_user_role_link_fk_role_id` (`role_id`),
  CONSTRAINT `t_user_role_link_fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `t_user_role_link_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_role_link
-- ----------------------------
INSERT INTO `t_user_role_link` VALUES ('9', '8');
INSERT INTO `t_user_role_link` VALUES ('10', '8');
INSERT INTO `t_user_role_link` VALUES ('11', '8');
INSERT INTO `t_user_role_link` VALUES ('12', '8');
INSERT INTO `t_user_role_link` VALUES ('13', '8');
INSERT INTO `t_user_role_link` VALUES ('14', '8');
INSERT INTO `t_user_role_link` VALUES ('15', '8');
INSERT INTO `t_user_role_link` VALUES ('16', '8');
INSERT INTO `t_user_role_link` VALUES ('17', '8');
INSERT INTO `t_user_role_link` VALUES ('18', '8');
INSERT INTO `t_user_role_link` VALUES ('19', '8');
INSERT INTO `t_user_role_link` VALUES ('20', '8');
INSERT INTO `t_user_role_link` VALUES ('21', '8');
INSERT INTO `t_user_role_link` VALUES ('22', '8');
INSERT INTO `t_user_role_link` VALUES ('23', '8');
INSERT INTO `t_user_role_link` VALUES ('24', '8');
INSERT INTO `t_user_role_link` VALUES ('25', '8');
INSERT INTO `t_user_role_link` VALUES ('9', '9');
INSERT INTO `t_user_role_link` VALUES ('10', '9');
INSERT INTO `t_user_role_link` VALUES ('11', '9');
INSERT INTO `t_user_role_link` VALUES ('12', '9');
INSERT INTO `t_user_role_link` VALUES ('13', '9');
INSERT INTO `t_user_role_link` VALUES ('14', '9');
INSERT INTO `t_user_role_link` VALUES ('15', '9');
INSERT INTO `t_user_role_link` VALUES ('16', '9');
INSERT INTO `t_user_role_link` VALUES ('17', '9');
INSERT INTO `t_user_role_link` VALUES ('18', '9');
INSERT INTO `t_user_role_link` VALUES ('19', '9');
INSERT INTO `t_user_role_link` VALUES ('20', '9');
INSERT INTO `t_user_role_link` VALUES ('21', '9');
INSERT INTO `t_user_role_link` VALUES ('22', '9');
INSERT INTO `t_user_role_link` VALUES ('23', '9');
INSERT INTO `t_user_role_link` VALUES ('24', '9');
INSERT INTO `t_user_role_link` VALUES ('25', '9');
INSERT INTO `t_user_role_link` VALUES ('9', '10');
INSERT INTO `t_user_role_link` VALUES ('10', '10');
INSERT INTO `t_user_role_link` VALUES ('11', '10');
INSERT INTO `t_user_role_link` VALUES ('12', '10');
INSERT INTO `t_user_role_link` VALUES ('13', '10');
INSERT INTO `t_user_role_link` VALUES ('14', '10');
INSERT INTO `t_user_role_link` VALUES ('15', '10');
INSERT INTO `t_user_role_link` VALUES ('16', '10');
INSERT INTO `t_user_role_link` VALUES ('17', '10');
INSERT INTO `t_user_role_link` VALUES ('18', '10');
INSERT INTO `t_user_role_link` VALUES ('19', '10');
INSERT INTO `t_user_role_link` VALUES ('20', '10');
INSERT INTO `t_user_role_link` VALUES ('21', '10');
INSERT INTO `t_user_role_link` VALUES ('22', '10');
INSERT INTO `t_user_role_link` VALUES ('23', '10');
INSERT INTO `t_user_role_link` VALUES ('24', '10');
INSERT INTO `t_user_role_link` VALUES ('25', '10');
INSERT INTO `t_user_role_link` VALUES ('9', '15');
INSERT INTO `t_user_role_link` VALUES ('10', '15');
INSERT INTO `t_user_role_link` VALUES ('11', '15');
INSERT INTO `t_user_role_link` VALUES ('12', '15');
INSERT INTO `t_user_role_link` VALUES ('13', '15');
INSERT INTO `t_user_role_link` VALUES ('14', '15');
INSERT INTO `t_user_role_link` VALUES ('15', '15');
INSERT INTO `t_user_role_link` VALUES ('16', '15');
INSERT INTO `t_user_role_link` VALUES ('17', '15');
INSERT INTO `t_user_role_link` VALUES ('18', '15');
INSERT INTO `t_user_role_link` VALUES ('19', '15');
INSERT INTO `t_user_role_link` VALUES ('20', '15');
INSERT INTO `t_user_role_link` VALUES ('21', '15');
INSERT INTO `t_user_role_link` VALUES ('22', '15');
INSERT INTO `t_user_role_link` VALUES ('23', '15');
INSERT INTO `t_user_role_link` VALUES ('24', '15');
INSERT INTO `t_user_role_link` VALUES ('25', '15');
INSERT INTO `t_user_role_link` VALUES ('9', '16');
INSERT INTO `t_user_role_link` VALUES ('10', '16');
INSERT INTO `t_user_role_link` VALUES ('11', '16');
INSERT INTO `t_user_role_link` VALUES ('12', '16');
INSERT INTO `t_user_role_link` VALUES ('13', '16');
INSERT INTO `t_user_role_link` VALUES ('14', '16');
INSERT INTO `t_user_role_link` VALUES ('15', '16');
INSERT INTO `t_user_role_link` VALUES ('16', '16');
INSERT INTO `t_user_role_link` VALUES ('17', '16');
INSERT INTO `t_user_role_link` VALUES ('18', '16');
INSERT INTO `t_user_role_link` VALUES ('19', '16');
INSERT INTO `t_user_role_link` VALUES ('20', '16');
INSERT INTO `t_user_role_link` VALUES ('21', '16');
INSERT INTO `t_user_role_link` VALUES ('22', '16');
INSERT INTO `t_user_role_link` VALUES ('23', '16');
INSERT INTO `t_user_role_link` VALUES ('24', '16');
INSERT INTO `t_user_role_link` VALUES ('25', '16');
INSERT INTO `t_user_role_link` VALUES ('9', '17');
INSERT INTO `t_user_role_link` VALUES ('10', '17');
INSERT INTO `t_user_role_link` VALUES ('11', '17');
INSERT INTO `t_user_role_link` VALUES ('12', '17');
INSERT INTO `t_user_role_link` VALUES ('13', '17');
INSERT INTO `t_user_role_link` VALUES ('14', '17');
INSERT INTO `t_user_role_link` VALUES ('15', '17');
INSERT INTO `t_user_role_link` VALUES ('16', '17');
INSERT INTO `t_user_role_link` VALUES ('17', '17');
INSERT INTO `t_user_role_link` VALUES ('18', '17');
INSERT INTO `t_user_role_link` VALUES ('19', '17');
INSERT INTO `t_user_role_link` VALUES ('20', '17');
INSERT INTO `t_user_role_link` VALUES ('21', '17');
INSERT INTO `t_user_role_link` VALUES ('22', '17');
INSERT INTO `t_user_role_link` VALUES ('23', '17');
INSERT INTO `t_user_role_link` VALUES ('24', '17');
INSERT INTO `t_user_role_link` VALUES ('25', '17');
INSERT INTO `t_user_role_link` VALUES ('9', '18');
INSERT INTO `t_user_role_link` VALUES ('10', '18');
INSERT INTO `t_user_role_link` VALUES ('11', '18');
INSERT INTO `t_user_role_link` VALUES ('12', '18');
INSERT INTO `t_user_role_link` VALUES ('13', '18');
INSERT INTO `t_user_role_link` VALUES ('14', '18');
INSERT INTO `t_user_role_link` VALUES ('15', '18');
INSERT INTO `t_user_role_link` VALUES ('16', '18');
INSERT INTO `t_user_role_link` VALUES ('17', '18');
INSERT INTO `t_user_role_link` VALUES ('18', '18');
INSERT INTO `t_user_role_link` VALUES ('19', '18');
INSERT INTO `t_user_role_link` VALUES ('20', '18');
INSERT INTO `t_user_role_link` VALUES ('21', '18');
INSERT INTO `t_user_role_link` VALUES ('22', '18');
INSERT INTO `t_user_role_link` VALUES ('23', '18');
INSERT INTO `t_user_role_link` VALUES ('24', '18');
INSERT INTO `t_user_role_link` VALUES ('25', '18');
INSERT INTO `t_user_role_link` VALUES ('9', '19');
INSERT INTO `t_user_role_link` VALUES ('10', '19');
INSERT INTO `t_user_role_link` VALUES ('11', '19');
INSERT INTO `t_user_role_link` VALUES ('12', '19');
INSERT INTO `t_user_role_link` VALUES ('13', '19');
INSERT INTO `t_user_role_link` VALUES ('14', '19');
INSERT INTO `t_user_role_link` VALUES ('15', '19');
INSERT INTO `t_user_role_link` VALUES ('16', '19');
INSERT INTO `t_user_role_link` VALUES ('17', '19');
INSERT INTO `t_user_role_link` VALUES ('18', '19');
INSERT INTO `t_user_role_link` VALUES ('19', '19');
INSERT INTO `t_user_role_link` VALUES ('20', '19');
INSERT INTO `t_user_role_link` VALUES ('21', '19');
INSERT INTO `t_user_role_link` VALUES ('22', '19');
INSERT INTO `t_user_role_link` VALUES ('23', '19');
INSERT INTO `t_user_role_link` VALUES ('24', '19');
INSERT INTO `t_user_role_link` VALUES ('25', '19');

-- ----------------------------
-- View structure for t_attachment_view
-- ----------------------------
DROP VIEW IF EXISTS `t_attachment_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`skip-grants user`@`skip-grants host` SQL SECURITY DEFINER VIEW `t_attachment_view` AS select `t_attachment`.`id` AS `id`,`t_attachment`.`attachment_name` AS `attachment_name`,`t_attachment`.`attachment_address` AS `attachment_address`,`t_attachment`.`attachment_path` AS `attachment_path`,`t_attachment`.`create_time` AS `create_time`,(case when (`t_user`.`head_img_id` is not null) then 't_user.head_img_id' when (`tmal`.`attachment_id` is not null) then 't_mail_attachment_link.attachment_id' else NULL end) AS `link_info` from ((`t_attachment` left join `t_user` on((`t_attachment`.`id` = `t_user`.`head_img_id`))) left join `t_mail_attachment_link` `tmal` on((`t_attachment`.`id` = `tmal`.`attachment_id`))) ;

-- ----------------------------
-- View structure for t_mail_to_users
-- ----------------------------
DROP VIEW IF EXISTS `t_mail_to_users`;
CREATE ALGORITHM=UNDEFINED DEFINER=`skip-grants user`@`skip-grants host` SQL SECURITY DEFINER VIEW `t_mail_to_users` AS select `t_mail_to_user_link`.`mail_id` AS `mail_id`,group_concat(`t_user`.`username` separator ',') AS `info` from (`t_mail_to_user_link` left join `t_user` on((`t_user`.`id` = `t_mail_to_user_link`.`to_user_id`))) group by `t_mail_to_user_link`.`mail_id` ;

-- ----------------------------
-- View structure for t_role_resources_info
-- ----------------------------
DROP VIEW IF EXISTS `t_role_resources_info`;
CREATE ALGORITHM=UNDEFINED DEFINER=`skip-grants user`@`skip-grants host` SQL SECURITY DEFINER VIEW `t_role_resources_info` AS select `t_role`.`id` AS `id`,group_concat(`t_resource`.`resource_pattern` separator ',') AS `resources_info` from ((`t_role` left join `t_role_resource_link` on((`t_role`.`id` = `t_role_resource_link`.`role_id`))) left join `t_resource` on((`t_role_resource_link`.`resource_id` = `t_resource`.`id`))) group by `t_role`.`id` ;
