/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-12 18:33:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attachment_name` varchar(200) DEFAULT NULL COMMENT '附件名称',
  `attachment_address` varchar(200) DEFAULT NULL COMMENT '访问地址',
  `attachment_path` varchar(200) DEFAULT NULL COMMENT '本地路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_attachment
-- ----------------------------
INSERT INTO `t_attachment` VALUES ('9', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571592112.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571592112.rar');
INSERT INTO `t_attachment` VALUES ('10', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571597869.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571597869.rar');
INSERT INTO `t_attachment` VALUES ('11', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571598898.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571598898.rar');
INSERT INTO `t_attachment` VALUES ('12', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571600367.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571600367.rar');
INSERT INTO `t_attachment` VALUES ('13', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571630659.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571630659.rar');
INSERT INTO `t_attachment` VALUES ('14', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571631448.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571631448.rar');
INSERT INTO `t_attachment` VALUES ('15', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571632180.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571632180.rar');
INSERT INTO `t_attachment` VALUES ('16', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571634337.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571634337.rar');
INSERT INTO `t_attachment` VALUES ('17', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571635176.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571635176.rar');
INSERT INTO `t_attachment` VALUES ('18', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571635889.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571635889.rar');
INSERT INTO `t_attachment` VALUES ('19', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571636706.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571636706.rar');
INSERT INTO `t_attachment` VALUES ('20', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571637506.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571637506.rar');
INSERT INTO `t_attachment` VALUES ('21', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571638284.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571638284.rar');
INSERT INTO `t_attachment` VALUES ('23', 'apache-tomcat-8.5.4-windows-x64.rar', '/demofile/abc/2019/3/1553571663964.rar', 'E:/IdeaProjects/file/abc/2019/3/1553571663964.rar');
INSERT INTO `t_attachment` VALUES ('24', 'exe4j_64b_jb51.rar', '/demofile/abc/2019/3/1553700244598.rar', 'E:/IdeaProjects/file/abc/2019/3/1553700244598.rar');
INSERT INTO `t_attachment` VALUES ('25', 'exe4j_64b_jb51.rar', '/demofile/abc/2019/3/1553700913857.rar', 'E:/IdeaProjects/file/abc/2019/3/1553700913857.rar');
INSERT INTO `t_attachment` VALUES ('26', 'exe4j_64b_jb51.rar', '/demofile/abc/2019/3/1553700924452.rar', 'E:/IdeaProjects/file/abc/2019/3/1553700924452.rar');
INSERT INTO `t_attachment` VALUES ('27', '桌面.rar', '/demofile/abc/2019/3/1553701128644.rar', 'E:/IdeaProjects/file/abc/2019/3/1553701128644.rar');
INSERT INTO `t_attachment` VALUES ('28', '1553701128644.rar', '/demofile/bcd/2019/3/1553701660654.rar', 'E:/IdeaProjects/file/bcd/2019/3/1553701660654.rar');
INSERT INTO `t_attachment` VALUES ('29', '1553701128644.rar', '/demofile/eee/2019/3/1553940007584.rar', 'E:/IdeaProjects/file/eee/2019/3/1553940007584.rar');
INSERT INTO `t_attachment` VALUES ('30', '1553701128644.rar', '/demofile/ceshi/2019/4/1554529074722.rar', 'E:/IdeaProjects/file/ceshi/2019/4/1554529074722.rar');
INSERT INTO `t_attachment` VALUES ('31', '1553700913857.rar', '/demofile/ceshi/2019/4/1554529266591.rar', 'E:/IdeaProjects/file/ceshi/2019/4/1554529266591.rar');
INSERT INTO `t_attachment` VALUES ('32', '1553700913857.rar', '/demofile/ceshi/2019/4/1554529387508.rar', 'E:/IdeaProjects/file/ceshi/2019/4/1554529387508.rar');

-- ----------------------------
-- Table structure for t_broadcast_message
-- ----------------------------
DROP TABLE IF EXISTS `t_broadcast_message`;
CREATE TABLE `t_broadcast_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_time` timestamp NULL DEFAULT NULL COMMENT '发送时间',
  `content` varchar(2000) DEFAULT NULL COMMENT '消息内容',
  `send_user_id` int(11) DEFAULT NULL COMMENT '发送用户id',
  PRIMARY KEY (`id`),
  KEY `t_broadcast_message_fk_send_user_id` (`send_user_id`),
  CONSTRAINT `t_broadcast_message_fk_send_user_id` FOREIGN KEY (`send_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_broadcast_message
-- ----------------------------
INSERT INTO `t_broadcast_message` VALUES ('1', '2019-04-11 00:00:14', '下面广播通知：斯凯孚红烧', '1');
INSERT INTO `t_broadcast_message` VALUES ('2', '2019-04-11 00:02:03', '下面广播通知：斯凯孚红烧', '1');
INSERT INTO `t_broadcast_message` VALUES ('3', '2019-04-11 00:03:27', '下面广播通知：斯凯孚红烧', '1');
INSERT INTO `t_broadcast_message` VALUES ('4', '2019-04-11 00:04:20', '下面广播通知：斯凯孚红烧', '1');
INSERT INTO `t_broadcast_message` VALUES ('5', '2019-04-11 00:04:28', '下面广播通知：斯凯孚红烧ffgf', '1');
INSERT INTO `t_broadcast_message` VALUES ('6', '2019-04-11 00:08:29', '下面广播通知：红烧肉', '1');
INSERT INTO `t_broadcast_message` VALUES ('7', '2019-04-11 00:09:37', '下面广播通知：红烧肉', '1');
INSERT INTO `t_broadcast_message` VALUES ('8', '2019-04-11 10:54:25', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('9', '2019-04-11 10:54:37', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('10', '2019-04-11 10:55:26', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('11', '2019-04-11 11:04:43', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('12', '2019-04-11 11:06:36', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('13', '2019-04-11 11:08:23', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('14', '2019-04-11 11:48:52', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('15', '2019-04-11 11:55:04', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('16', '2019-04-11 11:55:15', '群发消息', '1');
INSERT INTO `t_broadcast_message` VALUES ('17', '2019-04-11 16:06:35', '我是一条广播', '1');
INSERT INTO `t_broadcast_message` VALUES ('18', '2019-04-11 16:11:07', '我是一条广播', '1');

-- ----------------------------
-- Table structure for t_chat_message
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_message`;
CREATE TABLE `t_chat_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_time` timestamp NULL DEFAULT NULL COMMENT '发送时间',
  `content` varchar(2000) DEFAULT NULL COMMENT '消息内容',
  `send_user_id` int(11) DEFAULT NULL COMMENT '发送用户id',
  `to_user_id` int(11) DEFAULT NULL COMMENT '接收用户id',
  `read_status` tinyint(1) DEFAULT NULL COMMENT '阅读状态，1已读，0未读',
  PRIMARY KEY (`id`),
  KEY `t_single_message_fk_send_user_id` (`send_user_id`),
  KEY `t_single_message_fk_to_user_id` (`to_user_id`),
  CONSTRAINT `t_single_message_fk_send_user_id` FOREIGN KEY (`send_user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_single_message_fk_to_user_id` FOREIGN KEY (`to_user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_chat_message
-- ----------------------------
INSERT INTO `t_chat_message` VALUES ('1', '2019-04-11 17:30:33', '我是一条点对点消息', '1', '1', '1');
INSERT INTO `t_chat_message` VALUES ('2', '2019-04-11 17:31:44', '我是一条点对点消息', '1', '1', '1');
INSERT INTO `t_chat_message` VALUES ('3', '2019-04-11 17:33:12', '我是一条点对点消息', '1', '1', '0');

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '改下名');
INSERT INTO `t_dept` VALUES ('2', '测试部门');
INSERT INTO `t_dept` VALUES ('3', '改下名啊');
INSERT INTO `t_dept` VALUES ('4', '测试部门3');
INSERT INTO `t_dept` VALUES ('5', '5555');

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_type` varchar(50) DEFAULT NULL COMMENT '资源类型',
  `resource_pattern` varchar(50) DEFAULT NULL COMMENT '资源pattern',
  `resource_method` varchar(20) DEFAULT NULL COMMENT '请求方法',
  `resource_desc` varchar(50) DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', 'http', '/attachment/*/*', 'GET', '附件列表');
INSERT INTO `t_resource` VALUES ('2', 'http', '/attachment/*', 'GET', '附件详情');
INSERT INTO `t_resource` VALUES ('3', 'http', '/attachment/*', 'DELETE', '删除附件');
INSERT INTO `t_resource` VALUES ('4', 'http', '/dept/*/*', 'GET', '部门列表');
INSERT INTO `t_resource` VALUES ('5', 'http', '/dept/*', 'GET', '部门详情');
INSERT INTO `t_resource` VALUES ('6', 'http', '/dept', 'POST', '保存部门');
INSERT INTO `t_resource` VALUES ('7', 'http', '/dept', 'PUT', '更新部门');
INSERT INTO `t_resource` VALUES ('8', 'http', '/dept/*', 'DELETE', '删除部门');
INSERT INTO `t_resource` VALUES ('9', 'http', '/file/*/*', 'POST', '上传文件');
INSERT INTO `t_resource` VALUES ('10', 'http', '/resource/*/*', 'GET', '资源列表');
INSERT INTO `t_resource` VALUES ('11', 'http', '/resource/*', 'GET', '资源详情');
INSERT INTO `t_resource` VALUES ('12', 'http', '/resource', 'POST', '保存资源');
INSERT INTO `t_resource` VALUES ('13', 'http', '/resource', 'PUT', '更新资源');
INSERT INTO `t_resource` VALUES ('14', 'http', '/resource/*', 'DELETE', '删除资源');
INSERT INTO `t_resource` VALUES ('15', 'http', '/role/*/*', 'GET', '角色列表');
INSERT INTO `t_resource` VALUES ('16', 'http', '/role/*', 'GET', '角色详情');
INSERT INTO `t_resource` VALUES ('17', 'http', '/role', 'POST', '保存角色');
INSERT INTO `t_resource` VALUES ('18', 'http', '/role', 'PUT', '更新角色');
INSERT INTO `t_resource` VALUES ('19', 'http', '/role/*', 'DELETE', '删除角色');
INSERT INTO `t_resource` VALUES ('20', 'http', '/user/*/*', 'GET', '用户列表');
INSERT INTO `t_resource` VALUES ('21', 'http', '/user/*', 'GET', '用户详情');
INSERT INTO `t_resource` VALUES ('22', 'http', '/user', 'POST', '保存用户');
INSERT INTO `t_resource` VALUES ('23', 'http', '/user', 'PUT', '更新用户');
INSERT INTO `t_resource` VALUES ('24', 'http', '/user/*', 'DELETE', '删除用户');
INSERT INTO `t_resource` VALUES ('25', 'http', '/endpoint/**', null, '获取websocket信息及握手');
INSERT INTO `t_resource` VALUES ('26', 'websocket', '/topic/broadcast', 'SUBSCRIBE', '订阅广播消息');
INSERT INTO `t_resource` VALUES ('28', 'http', '/broadcast', 'POST', '发送广播消息');
INSERT INTO `t_resource` VALUES ('29', 'http', '/broadcast/*/*', 'GET', '广播列表');
INSERT INTO `t_resource` VALUES ('30', 'http', '/broadcast/*', 'GET', '广播详情');
INSERT INTO `t_resource` VALUES ('31', 'http', '/chat/*/*', 'GET', '消息列表');
INSERT INTO `t_resource` VALUES ('32', 'http', '/chat/*', 'GET', '消息详情');
INSERT INTO `t_resource` VALUES ('33', 'http', '/chat/*/*/*/*', 'GET', '往来消息');
INSERT INTO `t_resource` VALUES ('34', 'http', '/chat', 'PUT', '更新消息');
INSERT INTO `t_resource` VALUES ('35', 'http', '/chat', 'POST', '发送点对点消息');
INSERT INTO `t_resource` VALUES ('36', 'websocket', '/user/topic/chat', 'SUBSCRIBE', '订阅点对点消息');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(200) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_role_idx_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'ROLE_ADMIN', '管理员');
INSERT INTO `t_role` VALUES ('2', 'ROLE_MANAGER', '总经理');
INSERT INTO `t_role` VALUES ('3', 'ROLE_FINANCE', '财务');
INSERT INTO `t_role` VALUES ('4', 'ROLE_HR', '人资');
INSERT INTO `t_role` VALUES ('5', 'ROLE_STAFF', '员工');
INSERT INTO `t_role` VALUES ('6', 'ROLE_LOGIN', '登录用户');

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
INSERT INTO `t_role_resource_link` VALUES ('1', '1');
INSERT INTO `t_role_resource_link` VALUES ('5', '1');
INSERT INTO `t_role_resource_link` VALUES ('1', '2');
INSERT INTO `t_role_resource_link` VALUES ('5', '2');
INSERT INTO `t_role_resource_link` VALUES ('1', '3');
INSERT INTO `t_role_resource_link` VALUES ('1', '4');
INSERT INTO `t_role_resource_link` VALUES ('2', '4');
INSERT INTO `t_role_resource_link` VALUES ('4', '4');
INSERT INTO `t_role_resource_link` VALUES ('5', '4');
INSERT INTO `t_role_resource_link` VALUES ('1', '5');
INSERT INTO `t_role_resource_link` VALUES ('2', '5');
INSERT INTO `t_role_resource_link` VALUES ('4', '5');
INSERT INTO `t_role_resource_link` VALUES ('5', '5');
INSERT INTO `t_role_resource_link` VALUES ('1', '6');
INSERT INTO `t_role_resource_link` VALUES ('2', '6');
INSERT INTO `t_role_resource_link` VALUES ('4', '6');
INSERT INTO `t_role_resource_link` VALUES ('1', '7');
INSERT INTO `t_role_resource_link` VALUES ('2', '7');
INSERT INTO `t_role_resource_link` VALUES ('4', '7');
INSERT INTO `t_role_resource_link` VALUES ('1', '8');
INSERT INTO `t_role_resource_link` VALUES ('2', '8');
INSERT INTO `t_role_resource_link` VALUES ('4', '8');
INSERT INTO `t_role_resource_link` VALUES ('1', '9');
INSERT INTO `t_role_resource_link` VALUES ('3', '9');
INSERT INTO `t_role_resource_link` VALUES ('5', '9');
INSERT INTO `t_role_resource_link` VALUES ('1', '10');
INSERT INTO `t_role_resource_link` VALUES ('2', '10');
INSERT INTO `t_role_resource_link` VALUES ('4', '10');
INSERT INTO `t_role_resource_link` VALUES ('5', '10');
INSERT INTO `t_role_resource_link` VALUES ('1', '11');
INSERT INTO `t_role_resource_link` VALUES ('2', '11');
INSERT INTO `t_role_resource_link` VALUES ('4', '11');
INSERT INTO `t_role_resource_link` VALUES ('5', '11');
INSERT INTO `t_role_resource_link` VALUES ('1', '12');
INSERT INTO `t_role_resource_link` VALUES ('2', '12');
INSERT INTO `t_role_resource_link` VALUES ('4', '12');
INSERT INTO `t_role_resource_link` VALUES ('1', '13');
INSERT INTO `t_role_resource_link` VALUES ('2', '13');
INSERT INTO `t_role_resource_link` VALUES ('4', '13');
INSERT INTO `t_role_resource_link` VALUES ('1', '14');
INSERT INTO `t_role_resource_link` VALUES ('2', '14');
INSERT INTO `t_role_resource_link` VALUES ('4', '14');
INSERT INTO `t_role_resource_link` VALUES ('1', '15');
INSERT INTO `t_role_resource_link` VALUES ('2', '15');
INSERT INTO `t_role_resource_link` VALUES ('4', '15');
INSERT INTO `t_role_resource_link` VALUES ('5', '15');
INSERT INTO `t_role_resource_link` VALUES ('1', '16');
INSERT INTO `t_role_resource_link` VALUES ('2', '16');
INSERT INTO `t_role_resource_link` VALUES ('4', '16');
INSERT INTO `t_role_resource_link` VALUES ('5', '16');
INSERT INTO `t_role_resource_link` VALUES ('1', '17');
INSERT INTO `t_role_resource_link` VALUES ('2', '17');
INSERT INTO `t_role_resource_link` VALUES ('4', '17');
INSERT INTO `t_role_resource_link` VALUES ('1', '18');
INSERT INTO `t_role_resource_link` VALUES ('2', '18');
INSERT INTO `t_role_resource_link` VALUES ('4', '18');
INSERT INTO `t_role_resource_link` VALUES ('1', '19');
INSERT INTO `t_role_resource_link` VALUES ('2', '19');
INSERT INTO `t_role_resource_link` VALUES ('4', '19');
INSERT INTO `t_role_resource_link` VALUES ('1', '20');
INSERT INTO `t_role_resource_link` VALUES ('2', '20');
INSERT INTO `t_role_resource_link` VALUES ('3', '20');
INSERT INTO `t_role_resource_link` VALUES ('4', '20');
INSERT INTO `t_role_resource_link` VALUES ('5', '20');
INSERT INTO `t_role_resource_link` VALUES ('1', '21');
INSERT INTO `t_role_resource_link` VALUES ('2', '21');
INSERT INTO `t_role_resource_link` VALUES ('3', '21');
INSERT INTO `t_role_resource_link` VALUES ('4', '21');
INSERT INTO `t_role_resource_link` VALUES ('5', '21');
INSERT INTO `t_role_resource_link` VALUES ('1', '22');
INSERT INTO `t_role_resource_link` VALUES ('2', '22');
INSERT INTO `t_role_resource_link` VALUES ('4', '22');
INSERT INTO `t_role_resource_link` VALUES ('1', '23');
INSERT INTO `t_role_resource_link` VALUES ('2', '23');
INSERT INTO `t_role_resource_link` VALUES ('4', '23');
INSERT INTO `t_role_resource_link` VALUES ('1', '24');
INSERT INTO `t_role_resource_link` VALUES ('2', '24');
INSERT INTO `t_role_resource_link` VALUES ('4', '24');
INSERT INTO `t_role_resource_link` VALUES ('1', '25');
INSERT INTO `t_role_resource_link` VALUES ('1', '26');
INSERT INTO `t_role_resource_link` VALUES ('1', '28');
INSERT INTO `t_role_resource_link` VALUES ('1', '29');
INSERT INTO `t_role_resource_link` VALUES ('1', '30');
INSERT INTO `t_role_resource_link` VALUES ('1', '31');
INSERT INTO `t_role_resource_link` VALUES ('1', '32');
INSERT INTO `t_role_resource_link` VALUES ('1', '33');
INSERT INTO `t_role_resource_link` VALUES ('1', '34');
INSERT INTO `t_role_resource_link` VALUES ('1', '35');
INSERT INTO `t_role_resource_link` VALUES ('1', '36');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `birth` date DEFAULT NULL COMMENT '生日',
  `logintime` timestamp NULL DEFAULT NULL COMMENT '登陆时间',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_idx_username` (`username`),
  KEY `t_user_fk_dept_id` (`dept_id`),
  CONSTRAINT `t_user_fk_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'xlk', '$2a$10$yINz8uU8ZxNcseNx/MWjAuKFZLB8S7GBI2RmWJQnYLmvkSri5Dw8a', '薛凌康', '1990-10-14', '2019-03-24 11:25:54', '1');
INSERT INTO `t_user` VALUES ('2', 'xue', '$2a$10$yINz8uU8ZxNcseNx/MWjAuKFZLB8S7GBI2RmWJQnYLmvkSri5Dw8a', '薛', '1990-10-14', '2019-03-24 11:25:54', '1');
INSERT INTO `t_user` VALUES ('3', 'bcd', '$2a$10$yINz8uU8ZxNcseNx/MWjAuKFZLB8S7GBI2RmWJQnYLmvkSri5Dw8a', null, null, null, '1');
INSERT INTO `t_user` VALUES ('5', 'def', '$2a$10$yINz8uU8ZxNcseNx/MWjAuKFZLB8S7GBI2RmWJQnYLmvkSri5Dw8a', null, null, null, '1');

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
INSERT INTO `t_user_role_link` VALUES ('1', '1');
INSERT INTO `t_user_role_link` VALUES ('2', '1');
