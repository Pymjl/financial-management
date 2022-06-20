/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : financial-management

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 20/06/2022 22:02:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for airui_zhangwu
-- ----------------------------
DROP TABLE IF EXISTS `airui_zhangwu`;
CREATE TABLE `airui_zhangwu`  (
  `zwid` int(11) NOT NULL AUTO_INCREMENT,
  `flname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `money` double NULL DEFAULT NULL,
  `zhanghu` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` date NULL DEFAULT NULL,
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`zwid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of airui_zhangwu
-- ----------------------------
INSERT INTO `airui_zhangwu` VALUES (1, '吃饭饭', 1000, '工商银行', '2016-03-02', '和朋友聚餐', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (3, '服装支出', 1998, '现金', '2016-04-02', '买衣服', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (4, '吃饭支出', 325, '现金', '2016-06-18', '朋友聚餐', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (5, '股票收入', 8000, '工商银行', '2016-10-28', '股票大涨', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (6, '股票收入', 22, '工商银行', '2016-10-28', '股票又大涨', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (7, '工资收入', 5000, '交通银行', '2016-10-28', '又开工资了', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (8, '礼金支出', 5000, '现金', '2016-10-28', '朋友结婚', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (9, '其他支出', 1560, '现金', '2016-10-29', '丢钱了', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (10, '交通支出', 2300, '交通银行', '2016-10-29', '油价还在涨啊', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (11, '吃饭支出', 1000, '工商银行', '2016-10-29', '又吃饭', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (12, '工资收入', 1000, '现金', '2016-10-30', '开资', 'qiweikai');
INSERT INTO `airui_zhangwu` VALUES (13, '交通支出', 2000, '现金', '2016-10-30', '机票好贵', 'qqq');
INSERT INTO `airui_zhangwu` VALUES (14, '工资收入', 5000, '现金', '2016-10-30', '又开资', 'qqq');
INSERT INTO `airui_zhangwu` VALUES (16, '吃饭支出', 100, '农商银行', '2022-06-20', '和朋友聚餐', 'qiweikai');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `'unique_username'`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'qiweikai', '123456');
INSERT INTO `user` VALUES (2, 'pymjl', '123456');
INSERT INTO `user` VALUES (5, 'test', '123456');

SET FOREIGN_KEY_CHECKS = 1;
