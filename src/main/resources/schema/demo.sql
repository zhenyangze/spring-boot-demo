/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-30 21:21:45
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_attachment_idx_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

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

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) DEFAULT NULL COMMENT '书籍名称',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_book_idx_id` (`id`),
  KEY `t_book_fk_user_id` (`user_id`),
  CONSTRAINT `t_book_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('1', '啊啊啊', '1');
INSERT INTO `t_book` VALUES ('2', 'aaa', '1');
INSERT INTO `t_book` VALUES ('3', 'hhh', '1');

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_dept_idx_id` (`id`)
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
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `birth` date DEFAULT NULL COMMENT '生日',
  `logintime` timestamp NULL DEFAULT NULL COMMENT '登陆时间',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_idx_id` (`id`),
  KEY `t_user_fk_dept_id` (`dept_id`),
  CONSTRAINT `t_user_fk_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'xlk', '123', '薛凌康', '1990-10-14', '2019-03-24 11:25:54', '1');
INSERT INTO `t_user` VALUES ('2', 'xue', '123', '薛', '1990-10-14', '2019-03-24 11:25:54', '1');
INSERT INTO `t_user` VALUES ('3', 'bcd', '123', null, null, null, '1');
INSERT INTO `t_user` VALUES ('5', 'def', '123', null, null, null, '1');
