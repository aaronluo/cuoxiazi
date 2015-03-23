/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : eorder

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-03-23 09:38:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(128) DEFAULT NULL,
  `category_picture` varchar(256) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '湘菜', null, '2014-12-12 12:26:45', null);
INSERT INTO `t_category` VALUES ('2', '川菜', null, '2014-12-12 12:26:46', null);
INSERT INTO `t_category` VALUES ('3', '粤菜', null, '2014-12-12 12:26:47', null);
INSERT INTO `t_category` VALUES ('4', '小炒', null, '2014-12-12 12:26:48', null);
INSERT INTO `t_category` VALUES ('5', '海鲜', null, '2014-12-12 12:26:49', null);
INSERT INTO `t_category` VALUES ('6', '酒水', null, '2014-12-12 12:26:50', null);
INSERT INTO `t_category` VALUES ('7', '饮料', null, '2014-12-12 12:26:51', null);
INSERT INTO `t_category` VALUES ('8', '点心', null, '2014-12-12 12:26:52', null);
INSERT INTO `t_category` VALUES ('9', '主食', null, '2014-12-12 12:26:53', null);
INSERT INTO `t_category` VALUES ('10', '炖品', null, '2014-12-12 12:26:53', null);

-- ----------------------------
-- Table structure for `t_dish`
-- ----------------------------
DROP TABLE IF EXISTS `t_dish`;
CREATE TABLE `t_dish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `dish_name` varchar(128) DEFAULT NULL,
  `dish_picture` varchar(256) DEFAULT NULL,
  `dish_price` float(64,0) DEFAULT NULL,
  `on_sell` tinyint(1) DEFAULT NULL,
  `misc` varchar(128) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dish
-- ----------------------------
INSERT INTO `t_dish` VALUES ('1', '1', '手撕包菜', '/01/0101.png', '20', '1', null, '2014-12-12 13:26:01', null);
INSERT INTO `t_dish` VALUES ('2', '1', '无油版左公鸡', '/01/0102.png', '25', '1', null, '2014-12-12 13:26:02', null);
INSERT INTO `t_dish` VALUES ('3', '1', '毛氏红烧肉', '/01/0103.png', '30', '1', null, '2014-12-12 13:26:03', null);
INSERT INTO `t_dish` VALUES ('4', '1', '泡椒风爪', '/01/0104.png', '20', '1', null, '2014-12-12 13:26:04', null);
INSERT INTO `t_dish` VALUES ('5', '1', '豆豉炒香干', '/01/0105.png', '25', '1', null, '2014-12-12 13:26:05', null);
INSERT INTO `t_dish` VALUES ('6', '1', '辣子鸡', '/01/0106.png', '30', '1', null, '2014-12-12 13:26:06', null);
INSERT INTO `t_dish` VALUES ('7', '2', '剁椒鱼头', '/02/0201.png', '20', '1', null, '2014-12-12 13:26:07', null);
INSERT INTO `t_dish` VALUES ('8', '2', '口水鸡', '/02/0202.png', '25', '1', null, '2014-12-12 13:26:08', null);
INSERT INTO `t_dish` VALUES ('9', '2', '四川咸烧白', '/02/0203.png', '30', '1', null, '2014-12-12 13:26:09', null);
INSERT INTO `t_dish` VALUES ('10', '2', '川椒大蒜肚条', '/02/0204.png', '20', '1', null, '2014-12-12 13:26:10', null);
INSERT INTO `t_dish` VALUES ('11', '2', '水煮牛肉', '/02/0205.png', '25', '1', null, '2014-12-12 13:26:11', null);
INSERT INTO `t_dish` VALUES ('12', '3', '宫保鸡丁', '/03/0301.png', '30', '1', null, '2014-12-12 13:26:12', null);
INSERT INTO `t_dish` VALUES ('13', '3', '支竹焖羊肉', '/03/0302.png', '20', '1', null, '2014-12-12 13:26:13', null);
INSERT INTO `t_dish` VALUES ('14', '3', '腊鸭腿煲仔饭', '/03/0303.png', '25', '1', null, '2014-12-12 13:26:14', null);
INSERT INTO `t_dish` VALUES ('15', '3', '菠萝咕咾肉', '/03/0304.png', '30', '1', null, '2014-12-12 13:26:15', null);
INSERT INTO `t_dish` VALUES ('16', '3', '菰米腊鸭腿煲仔饭', '/03/0305.png', '20', '1', null, '2014-12-12 13:26:16', null);
INSERT INTO `t_dish` VALUES ('17', '3', '蒜蓉粉丝蒸扇贝', '/03/0306.png', '25', '1', null, '2014-12-12 13:26:17', null);
INSERT INTO `t_dish` VALUES ('18', '3', '豉香蒸排骨', '/03/0307.png', '30', '1', null, '2014-12-12 13:26:18', null);
INSERT INTO `t_dish` VALUES ('19', '4', '宫保虾灯', '/04/0401.png', '20', '1', null, '2014-12-12 13:26:19', null);
INSERT INTO `t_dish` VALUES ('20', '4', '木耳炒白菜', '/04/0402.png', '25', '1', null, '2014-12-12 13:26:20', null);
INSERT INTO `t_dish` VALUES ('21', '4', '泡菜五花肉', '/04/0403.png', '30', '1', null, '2014-12-12 13:26:21', null);
INSERT INTO `t_dish` VALUES ('22', '4', '滑蛋虾仁', '/04/0404.png', '20', '1', null, '2014-12-12 13:26:22', null);
INSERT INTO `t_dish` VALUES ('23', '4', '软炸虾仁', '/04/0405.png', '25', '1', null, '2014-12-12 13:26:23', null);
INSERT INTO `t_dish` VALUES ('24', '4', '香醋花生', '/04/0406.png', '30', '1', null, '2014-12-12 13:26:24', null);
INSERT INTO `t_dish` VALUES ('25', '5', '上汤龙虾球', '/05/0501.png', '20', '1', null, '2014-12-12 13:26:25', null);
INSERT INTO `t_dish` VALUES ('26', '5', '文蛤炖蛋', '/05/0502.png', '25', '1', null, '2014-12-12 13:26:26', null);
INSERT INTO `t_dish` VALUES ('27', '5', '核桃虾仁', '/05/0503.png', '30', '1', null, '2014-12-12 13:26:27', null);
INSERT INTO `t_dish` VALUES ('28', '5', '白灼花蟹', '/05/0504.png', '20', '1', null, '2014-12-12 13:26:28', null);
INSERT INTO `t_dish` VALUES ('29', '5', '白灼虾', '/05/0505.png', '25', '1', null, '2014-12-12 13:26:29', null);
INSERT INTO `t_dish` VALUES ('30', '5', '龙井虾仁', '/05/0506.png', '30', '1', null, '2014-12-12 13:26:30', null);
INSERT INTO `t_dish` VALUES ('31', '6', '九江双蒸酒', '/06/0601.png', '20', '1', null, '2014-12-12 13:26:31', null);
INSERT INTO `t_dish` VALUES ('32', '6', '小糊涂神', '/06/0602.png', '25', '1', null, '2014-12-12 13:26:32', null);
INSERT INTO `t_dish` VALUES ('33', '6', '泸州老窖', '/06/0603.png', '30', '1', null, '2014-12-12 13:26:33', null);
INSERT INTO `t_dish` VALUES ('34', '6', '红秀天香', '/06/0604.png', '20', '1', null, '2014-12-12 13:26:34', null);
INSERT INTO `t_dish` VALUES ('35', '6', '贵州茅台酒', '/06/0605.png', '25', '1', null, '2014-12-12 13:26:35', null);
INSERT INTO `t_dish` VALUES ('36', '6', '长城干红葡萄酒', '/06/0606.png', '30', '1', null, '2014-12-12 13:26:36', null);
INSERT INTO `t_dish` VALUES ('37', '7', '冰红茶', '/07/0701.png', '20', '1', null, '2014-12-12 13:26:37', null);
INSERT INTO `t_dish` VALUES ('38', '7', '奶茶', '/07/0702.png', '25', '1', null, '2014-12-12 13:26:38', null);
INSERT INTO `t_dish` VALUES ('39', '7', '拿铁咖啡', '/07/0703.png', '30', '1', null, '2014-12-12 13:26:39', null);
INSERT INTO `t_dish` VALUES ('40', '7', '橙汁', '/07/0704.png', '20', '1', null, '2014-12-12 13:26:40', null);
INSERT INTO `t_dish` VALUES ('41', '7', '西瓜汁', '/07/0705.png', '25', '1', null, '2014-12-12 13:26:41', null);
INSERT INTO `t_dish` VALUES ('42', '7', '酸梅汤', '/07/0706.png', '30', '1', null, '2014-12-12 13:26:42', null);
INSERT INTO `t_dish` VALUES ('43', '8', '五仁月饼', '/08/0801.png', '20', '1', null, '2014-12-12 13:26:43', null);
INSERT INTO `t_dish` VALUES ('44', '8', '凉糕', '/08/0802.png', '25', '1', null, '2014-12-12 13:26:44', null);
INSERT INTO `t_dish` VALUES ('45', '8', '杯子蛋糕', '/08/0803.png', '30', '1', null, '2014-12-12 13:26:45', null);
INSERT INTO `t_dish` VALUES ('46', '8', '水果慕斯蛋糕', '/08/0804.png', '20', '1', null, '2014-12-12 13:26:46', null);
INSERT INTO `t_dish` VALUES ('47', '8', '蔓越莓小蛋糕', '/08/0805.png', '25', '1', null, '2014-12-12 13:26:47', null);
INSERT INTO `t_dish` VALUES ('48', '8', '香橙蛋糕卷', '/08/0806.png', '30', '1', null, '2014-12-12 13:26:48', null);
INSERT INTO `t_dish` VALUES ('49', '9', '南瓜饼', '/09/0901.png', '20', '1', null, '2014-12-12 13:26:49', null);
INSERT INTO `t_dish` VALUES ('50', '9', '热带海鲜炬饭', '/09/0902.png', '25', '1', null, '2014-12-12 13:26:50', null);
INSERT INTO `t_dish` VALUES ('51', '9', '紫薯花卷', '/09/0903.png', '30', '1', null, '2014-12-12 13:26:51', null);
INSERT INTO `t_dish` VALUES ('52', '9', '芋头腊味饭', '/09/0904.png', '20', '1', null, '2014-12-12 13:26:52', null);
INSERT INTO `t_dish` VALUES ('53', '9', '香煎土豆饼', '/09/0905.png', '25', '1', null, '2014-12-12 13:26:53', null);
INSERT INTO `t_dish` VALUES ('54', '9', '麻酱拌意面', '/09/0906.png', '30', '1', null, '2014-12-12 13:26:54', null);
INSERT INTO `t_dish` VALUES ('55', '10', '小鸡炖蘑菇', '/09/0901.png', '20', '1', null, '2014-12-12 13:26:49', null);
INSERT INTO `t_dish` VALUES ('56', '10', '番薯炖牛肉', '/09/0902.png', '25', '1', null, '2014-12-12 13:26:50', null);
INSERT INTO `t_dish` VALUES ('57', '10', '胡萝卜炖羊肉', '/09/0903.png', '30', '1', null, '2014-12-12 13:26:51', null);
INSERT INTO `t_dish` VALUES ('58', '10', '金针炖猪蹄', '/09/0904.png', '20', '1', null, '2014-12-12 13:26:52', null);
INSERT INTO `t_dish` VALUES ('59', '10', '鲜奶炖鸡汤', '/09/0905.png', '25', '1', null, '2014-12-12 13:26:53', null);

-- ----------------------------
-- Table structure for `t_function`
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `function_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'the name of function',
  `function_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'detail information',
  `function_picture` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `function_path` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'the access path to this function',
  `function_parent` bigint(20) DEFAULT NULL COMMENT 'parent function',
  `function_order` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT 'use to organize menu in order\r\n            the top function should be a integer as such 100000, 140000\r\n            the first 2 bit is for top function',
  `function_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='define system functions & path';

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES ('1', '权限管理', 'Administration', 'icon01.png', '', '0', '010000', '1', null, null);
INSERT INTO `t_function` VALUES ('2', '权限管理', 'Function', null, '/function/function.action', '1', '010300', '1', null, null);
INSERT INTO `t_function` VALUES ('3', '角色管理', 'Role', null, '/role/role.action', '1', '010200', '1', null, null);
INSERT INTO `t_function` VALUES ('4', '用户管理', 'User', null, '/user/user.action', '1', '010100', '1', null, null);
INSERT INTO `t_function` VALUES ('7', '会员管理', 'Configuration', 'icon02.png', null, '0', '020000', '1', null, null);
INSERT INTO `t_function` VALUES ('8', '个人设置', 'Personal', null, '/person/doPerson.action', '7', '020100', '1', null, null);
INSERT INTO `t_function` VALUES ('9', '密码重置', 'ResetPassword', null, '/resetpassword/doResetPassword', '7', '020200', '1', null, null);
INSERT INTO `t_function` VALUES ('10', '菜单管理', 'Test', 'icon03.png', '11', '0', '030000', '1', '2014-12-03 08:43:56', null);
INSERT INTO `t_function` VALUES ('36', '新功能', '新功能', null, '/test/doPerson1.action', '10', '030100', '1', null, '2014-12-15 06:20:01');
INSERT INTO `t_function` VALUES ('43', 'bbbb', 'bbbb', null, 'bbbba', '1', '010500', '1', null, null);
INSERT INTO `t_function` VALUES ('44', '设置2', '设置2', null, '设置24', '1', '010211', '1', null, null);
INSERT INTO `t_function` VALUES ('45', '设置3', '设置12', null, '设置12', '1', '030201', '1', null, null);
INSERT INTO `t_function` VALUES ('47', '流水管理2', '流水管理23', null, '流水管理2', '46', '060001', '1', null, null);
INSERT INTO `t_function` VALUES ('51', 'aggg', 'aaaa', null, 'aaaa', '7', null, '1', null, null);
INSERT INTO `t_function` VALUES ('52', '设置23', '设置23', null, '设置23', '1', null, '1', null, null);

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_seq` varchar(128) DEFAULT NULL,
  `table_number` int(8) DEFAULT NULL,
  `attendee_number` int(8) DEFAULT NULL,
  `total_price` float(64,2) DEFAULT NULL,
  `order_status` bigint(20) DEFAULT NULL,
  `servent_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `casher_id` bigint(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `discount_price` float(64,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_m_id` (`member_id`),
  KEY `fk_s_id` (`servent_id`),
  KEY `fk_c_id` (`casher_id`),
  CONSTRAINT `fk_c_id` FOREIGN KEY (`casher_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `fk_m_id` FOREIGN KEY (`member_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `fk_s_id` FOREIGN KEY (`servent_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '2014120900001', '10', '4', '150.00', '1', '1', '9', '1', '2014-12-08 19:48:34', null, '150.00');
INSERT INTO `t_order` VALUES ('2', '2014120900001', '112', '2', '100.50', '1', '1', '9', '1', '2014-12-09 20:48:44', null, '100.50');

-- ----------------------------
-- Table structure for `t_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自动增加',
  `order_id` bigint(20) DEFAULT NULL,
  `dish_id` bigint(20) DEFAULT NULL,
  `dish_amount` bigint(20) DEFAULT '1' COMMENT '份数，最大99',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES ('1', '1', '1', '1', null, null);
INSERT INTO `t_order_item` VALUES ('2', '1', '2', '1', null, null);
INSERT INTO `t_order_item` VALUES ('3', '1', '3', '2', null, null);
INSERT INTO `t_order_item` VALUES ('4', '2', '1', '2', null, null);
INSERT INTO `t_order_item` VALUES ('5', '2', '2', '1', null, null);
INSERT INTO `t_order_item` VALUES ('6', '2', '5', '1', null, null);

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `role_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `role_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '管理员', '1', null, '2014-12-16 08:24:15');
INSERT INTO `t_role` VALUES ('2', '普通用户', '普通用户', '1', null, null);
INSERT INTO `t_role` VALUES ('3', '测试员', '测试员', '1', null, '2014-12-11 08:36:25');
INSERT INTO `t_role` VALUES ('9', '服务员', '服务员', '1', null, '2014-12-11 08:36:40');
INSERT INTO `t_role` VALUES ('10', '收银员', '收银员', '1', null, '2014-12-16 12:20:23');
INSERT INTO `t_role` VALUES ('11', '菜品管理员', '菜品管理员', '1', null, '2014-12-14 11:44:36');
INSERT INTO `t_role` VALUES ('23', '用户管理员', '用户管理员6', '1', null, '2014-12-17 05:04:59');
INSERT INTO `t_role` VALUES ('25', '收银员2', '负责收银的员工1', '1', null, null);
INSERT INTO `t_role` VALUES ('27', '收银员6', '收银员6', '1', null, null);
INSERT INTO `t_role` VALUES ('30', '收银员56', '收银员56', '1', null, null);
INSERT INTO `t_role` VALUES ('31', '收银员3', '收银员3', '1', null, null);
INSERT INTO `t_role` VALUES ('32', '收银员4', '收银员4', '1', null, null);
INSERT INTO `t_role` VALUES ('33', '流水管理员', '流水管理员', '1', null, null);
INSERT INTO `t_role` VALUES ('35', '收银员8', '收银员7', '1', null, null);

-- ----------------------------
-- Table structure for `t_role_function`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_function`;
CREATE TABLE `t_role_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL DEFAULT '0',
  `function_id` bigint(20) NOT NULL DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_functionid` (`function_id`),
  KEY `fk_role_rf` (`role_id`),
  CONSTRAINT `fk_functionid` FOREIGN KEY (`function_id`) REFERENCES `t_function` (`id`),
  CONSTRAINT `fk_role_rf` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role_function
-- ----------------------------
INSERT INTO `t_role_function` VALUES ('76', '3', '7', '2014-12-11 08:36:25', null);
INSERT INTO `t_role_function` VALUES ('77', '3', '8', '2014-12-11 08:36:25', null);
INSERT INTO `t_role_function` VALUES ('78', '9', '7', '2014-12-11 08:36:40', null);
INSERT INTO `t_role_function` VALUES ('79', '9', '9', '2014-12-11 08:36:40', null);
INSERT INTO `t_role_function` VALUES ('106', '11', '7', '2014-12-14 11:44:36', null);
INSERT INTO `t_role_function` VALUES ('107', '11', '8', '2014-12-14 11:44:36', null);
INSERT INTO `t_role_function` VALUES ('108', '11', '9', '2014-12-14 11:44:36', null);
INSERT INTO `t_role_function` VALUES ('140', '10', '1', '2014-12-16 12:20:23', null);
INSERT INTO `t_role_function` VALUES ('141', '10', '3', '2014-12-16 12:20:23', null);
INSERT INTO `t_role_function` VALUES ('142', '10', '7', '2014-12-16 12:20:23', null);
INSERT INTO `t_role_function` VALUES ('143', '10', '8', '2014-12-16 12:20:23', null);
INSERT INTO `t_role_function` VALUES ('144', '10', '10', '2014-12-16 12:20:24', null);
INSERT INTO `t_role_function` VALUES ('145', '10', '36', '2014-12-16 12:20:24', null);
INSERT INTO `t_role_function` VALUES ('146', '1', '1', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('147', '1', '2', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('148', '1', '3', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('149', '1', '4', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('150', '1', '10', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('151', '1', '36', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('160', '23', '1', '2014-12-17 05:04:59', null);
INSERT INTO `t_role_function` VALUES ('161', '23', '4', '2014-12-17 05:04:59', null);
INSERT INTO `t_role_function` VALUES ('170', '2', '1', null, null);
INSERT INTO `t_role_function` VALUES ('171', '2', '2', null, null);
INSERT INTO `t_role_function` VALUES ('173', '23', '1', null, null);
INSERT INTO `t_role_function` VALUES ('174', '23', '4', null, null);
INSERT INTO `t_role_function` VALUES ('175', '23', '4', null, null);
INSERT INTO `t_role_function` VALUES ('176', '23', '1', null, null);
INSERT INTO `t_role_function` VALUES ('177', '11', '7', null, null);
INSERT INTO `t_role_function` VALUES ('178', '11', '3', null, null);
INSERT INTO `t_role_function` VALUES ('179', '11', '8', null, null);
INSERT INTO `t_role_function` VALUES ('180', '11', '9', null, null);
INSERT INTO `t_role_function` VALUES ('181', '11', '3', null, null);
INSERT INTO `t_role_function` VALUES ('182', '11', '7', null, null);
INSERT INTO `t_role_function` VALUES ('183', '11', '9', null, null);
INSERT INTO `t_role_function` VALUES ('184', '11', '8', null, null);
INSERT INTO `t_role_function` VALUES ('185', '11', '36', null, null);
INSERT INTO `t_role_function` VALUES ('276', '30', '4', null, null);
INSERT INTO `t_role_function` VALUES ('277', '30', '3', null, null);
INSERT INTO `t_role_function` VALUES ('278', '32', '3', null, null);
INSERT INTO `t_role_function` VALUES ('323', '25', '9', null, null);
INSERT INTO `t_role_function` VALUES ('324', '25', '36', null, null);
INSERT INTO `t_role_function` VALUES ('325', '27', '36', null, null);
INSERT INTO `t_role_function` VALUES ('326', '33', '47', null, null);
INSERT INTO `t_role_function` VALUES ('328', '31', '44', null, null);
INSERT INTO `t_role_function` VALUES ('333', '35', '8', null, null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `cellphone` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `user_score` bigint(20) DEFAULT NULL,
  `user_status` tinyint(1) DEFAULT '1',
  `user_level_id` bigint(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ul_id` (`user_level_id`),
  CONSTRAINT `fk_ul_id` FOREIGN KEY (`user_level_id`) REFERENCES `t_user_level` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='user''s basic information';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'ceb4f32325eda6142bd65215f4c0f371', '13912345671', null, '1', '1', '2012-09-29 00:00:00', null);
INSERT INTO `t_user` VALUES ('2', 'test', '889255f1c9c8f12a353be255f78a848b', '13912345672', null, '1', '2', '2012-09-29 00:00:00', '2014-12-14 10:45:54');
INSERT INTO `t_user` VALUES ('9', '王五', 'b3ccc74de29cd6dded5bac6677923ddb', '13912340003', null, '1', '1', null, '2014-12-14 10:53:46');
INSERT INTO `t_user` VALUES ('10', '赵六', 'b3ccc74de29cd6dded5bac6677923ddb', '13912340004', null, '1', '1', null, null);
INSERT INTO `t_user` VALUES ('35', 'abcd', 'cf3b0ef29697910a4e5c387dfc02ba66', '1231231211', null, '1', '4', null, null);
INSERT INTO `t_user` VALUES ('37', 'aaaa', '22c856182763372b82366410ba9cc6f0', '1231231213', null, '1', '4', null, null);
INSERT INTO `t_user` VALUES ('38', 'admin1234', '6272875d5402882361f0b6736737fbd8', '1231231288', null, '1', '4', null, null);
INSERT INTO `t_user` VALUES ('40', 'zhangsan', '8329902711e2630431af33cb3a94357f', '1231231234', null, '1', '4', null, null);
INSERT INTO `t_user` VALUES ('44', 'admin123', '0f2bd7b70abed919d3e470eecef5285f', '12345', null, '1', '4', null, null);
INSERT INTO `t_user` VALUES ('47', '111', '5e2382283ace1c150399130875a38a5e', '13925864587', null, '1', '4', null, null);
INSERT INTO `t_user` VALUES ('50', 'aaaaa', 'e098ba26017ab7abc9b61afdf0a25b68', '13945674567', null, '1', '4', null, null);

-- ----------------------------
-- Table structure for `t_user_level`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_level`;
CREATE TABLE `t_user_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level_name` varchar(128) DEFAULT NULL,
  `discount` float(32,2) DEFAULT NULL,
  `level_score` bigint(20) DEFAULT NULL,
  `level_status` tinyint(1) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_level
-- ----------------------------
INSERT INTO `t_user_level` VALUES ('1', '钻石会员', '8.00', '10000', '1', null, null);
INSERT INTO `t_user_level` VALUES ('2', '白金会员', '8.50', '7500', '1', null, null);
INSERT INTO `t_user_level` VALUES ('3', '金牌会员', '9.00', '5000', '1', null, null);
INSERT INTO `t_user_level` VALUES ('4', '普通会员', '9.50', '0', '1', null, null);

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_userid` (`user_id`),
  KEY `fk_roleid` (`role_id`),
  CONSTRAINT `fk_roleid` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('23', '9', '2', '2014-12-10 09:38:48', null);
INSERT INTO `t_user_role` VALUES ('24', '10', '2', '2014-12-10 09:41:10', null);
INSERT INTO `t_user_role` VALUES ('25', '35', '1', null, null);
INSERT INTO `t_user_role` VALUES ('28', '2', '2', null, null);
INSERT INTO `t_user_role` VALUES ('56', '38', '10', null, null);
INSERT INTO `t_user_role` VALUES ('57', '38', '30', null, null);
INSERT INTO `t_user_role` VALUES ('58', '40', '2', null, null);
INSERT INTO `t_user_role` VALUES ('64', '37', '25', null, null);
INSERT INTO `t_user_role` VALUES ('65', '37', '23', null, null);
INSERT INTO `t_user_role` VALUES ('66', '37', '2', null, null);
INSERT INTO `t_user_role` VALUES ('68', '44', '3', null, null);
INSERT INTO `t_user_role` VALUES ('76', '47', '25', null, null);
INSERT INTO `t_user_role` VALUES ('77', '47', '11', null, null);
INSERT INTO `t_user_role` VALUES ('78', '47', '2', null, null);
INSERT INTO `t_user_role` VALUES ('79', '47', '23', null, null);
INSERT INTO `t_user_role` VALUES ('80', '47', '10', null, null);
INSERT INTO `t_user_role` VALUES ('81', '47', '31', null, null);
INSERT INTO `t_user_role` VALUES ('82', '47', '32', null, null);
INSERT INTO `t_user_role` VALUES ('83', '47', '33', null, null);
INSERT INTO `t_user_role` VALUES ('84', '47', '30', null, null);
INSERT INTO `t_user_role` VALUES ('85', '47', '1', null, null);
INSERT INTO `t_user_role` VALUES ('86', '47', '3', null, null);
INSERT INTO `t_user_role` VALUES ('87', '47', '27', null, null);
INSERT INTO `t_user_role` VALUES ('88', '47', '9', null, null);
INSERT INTO `t_user_role` VALUES ('102', '1', '1', null, null);
INSERT INTO `t_user_role` VALUES ('106', '50', '35', null, null);
INSERT INTO `t_user_role` VALUES ('107', '50', '10', null, null);
INSERT INTO `t_user_role` VALUES ('108', '50', '9', null, null);
