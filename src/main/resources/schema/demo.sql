/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-23 23:44:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) DEFAULT NULL COMMENT '父部门id',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `dept_level` int(1) DEFAULT NULL COMMENT '部门级别',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_dept_idx_id` (`id`),
  KEY `t_dept_idx_p_id` (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_dept
-- ----------------------------

-- ----------------------------
-- Table structure for t_pri
-- ----------------------------
DROP TABLE IF EXISTS `t_pri`;
CREATE TABLE `t_pri` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) DEFAULT NULL COMMENT '父权限id',
  `pri_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `pri_alias` varchar(50) DEFAULT NULL COMMENT '权限别名',
  `pri_level` int(1) DEFAULT NULL COMMENT '权限级别',
  UNIQUE KEY `t_pri_idx_id` (`id`),
  KEY `t_pri_idx_p_id` (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_pri
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_role_idx_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_role_pri_link
-- ----------------------------
DROP TABLE IF EXISTS `t_role_pri_link`;
CREATE TABLE `t_role_pri_link` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `pri_id` int(11) NOT NULL COMMENT '权限id',
  `pri_scope` varchar(50) DEFAULT NULL COMMENT '权限作用范围',
  PRIMARY KEY (`role_id`,`pri_id`),
  UNIQUE KEY `t_role_pri_link_idx_pk` (`role_id`,`pri_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_role_pri_link
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_idx_id` (`id`),
  KEY `t_user_fk_dept_id` (`dept_id`),
  KEY `t_user_fk_role_id` (`role_id`),
  CONSTRAINT `t_user_fk_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`id`),
  CONSTRAINT `t_user_fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'xlk', '123', null, null);
