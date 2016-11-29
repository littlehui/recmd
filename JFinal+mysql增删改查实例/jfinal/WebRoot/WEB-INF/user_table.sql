/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : chm

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2014-02-25 16:13:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  `userName` varchar(256) DEFAULT NULL COMMENT '姓名',
  `id` int(8) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=543543562 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES ('432', '432', '4324324');
INSERT INTO `user_table` VALUES ('5435', '43', '5435435');
INSERT INTO `user_table` VALUES ('543543', '5345', '435435435');
INSERT INTO `user_table` VALUES ('543', '534', '543543554');
INSERT INTO `user_table` VALUES ('小崔', '小崔', '543543555');
INSERT INTO `user_table` VALUES (null, null, '543543556');
INSERT INTO `user_table` VALUES (null, null, '543543557');
INSERT INTO `user_table` VALUES (null, null, '543543558');
INSERT INTO `user_table` VALUES ('23213', '23323', '543543559');
INSERT INTO `user_table` VALUES ('22222222', '中国', '543543560');
INSERT INTO `user_table` VALUES ('3213213', '3213', '543543561');
