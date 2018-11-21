/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : data

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2018-11-21 17:47:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `seckill`
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `number` int(11) NOT NULL COMMENT '库存数量',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '秒杀开启的时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束的时间',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建的时间',
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`seckill_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('97', '1000元秒杀iphone6', '2018-11-21 17:44:36', '2018-11-30 00:00:00', '2018-11-07 22:35:45', '1000');
INSERT INTO `seckill` VALUES ('200', '500元秒杀iPad2', '2018-11-17 22:36:49', '2016-05-23 00:00:00', '2018-11-06 22:35:50', '1001');
INSERT INTO `seckill` VALUES ('300', '300元秒杀小米4', '2018-11-17 22:36:53', '2016-05-23 00:00:00', '2018-11-06 22:36:50', '1002');
INSERT INTO `seckill` VALUES ('400', '200元秒杀红米note', '2018-11-17 22:36:58', '2016-05-23 00:00:00', '2018-10-29 22:36:54', '1003');
