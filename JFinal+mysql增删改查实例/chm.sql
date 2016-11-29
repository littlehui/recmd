/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : chm

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2014-05-23 12:55:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user_table`
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  `userName` varchar(256) DEFAULT NULL COMMENT '姓名',
  `id` int(8) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES ('123', 'jww', '1');
INSERT INTO `user_table` VALUES ('123', '邵良菊', '2');
INSERT INTO `user_table` VALUES ('123', '陈伟', '3');
