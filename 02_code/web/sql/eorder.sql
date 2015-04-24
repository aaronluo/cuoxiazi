/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : eorder

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-04-24 16:21:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(128) NOT NULL,
  `category_picture` varchar(256) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '湘菜', '/default_dish.png', '2014-12-12 12:26:45', null);
INSERT INTO `t_category` VALUES ('2', '川菜', '/default_dish.png', '2014-12-12 12:26:46', null);
INSERT INTO `t_category` VALUES ('3', '粤菜', '/default_dish.png', '2014-12-12 12:26:47', null);
INSERT INTO `t_category` VALUES ('4', '小炒', '/default_dish.png', '2014-12-12 12:26:48', null);
INSERT INTO `t_category` VALUES ('5', '海鲜', '/default_dish.png', '2014-12-12 12:26:49', null);
INSERT INTO `t_category` VALUES ('6', '酒水', '/default_dish.png', '2014-12-12 12:26:50', null);
INSERT INTO `t_category` VALUES ('7', '饮料', '/default_dish.png', '2014-12-12 12:26:51', null);
INSERT INTO `t_category` VALUES ('8', '点心', '/default_dish.png', '2014-12-12 12:26:52', null);
INSERT INTO `t_category` VALUES ('9', '主食', '/default_dish.png', '2014-12-12 12:26:53', null);
INSERT INTO `t_category` VALUES ('10', '炖品', '/default_dish.png', '2014-12-12 12:26:53', null);
INSERT INTO `t_category` VALUES ('11', '默认', '/default_dish.png', '2014-12-12 12:26:53', null);

-- ----------------------------
-- Table structure for `t_dish`
-- ----------------------------
DROP TABLE IF EXISTS `t_dish`;
CREATE TABLE `t_dish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) DEFAULT NULL,
  `dish_name` varchar(128) NOT NULL,
  `dish_picture` varchar(256) DEFAULT NULL,
  `dish_price` float(64,2) DEFAULT NULL,
  `on_sell` tinyint(1) DEFAULT '1',
  `misc` varchar(128) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dish
-- ----------------------------
INSERT INTO `t_dish` VALUES ('1', '1', '手撕包菜', '/01/0101.png', '20.00', '1', null, '2014-12-12 13:26:01', null);
INSERT INTO `t_dish` VALUES ('2', '1', '无油版左公鸡', '/01/0102.png', '25.01', '1', null, '2014-12-12 13:26:02', null);
INSERT INTO `t_dish` VALUES ('3', '1', '毛氏红烧肉', '/01/0103.png', '30.00', '1', null, '2014-12-12 13:26:03', null);
INSERT INTO `t_dish` VALUES ('4', '1', '泡椒风爪', '/01/0104.png', '20.00', '1', null, '2014-12-12 13:26:04', null);
INSERT INTO `t_dish` VALUES ('5', '1', '豆豉炒香干', '/01/0105.png', '25.00', '1', null, '2014-12-12 13:26:05', null);
INSERT INTO `t_dish` VALUES ('6', '1', '辣子鸡', '/01/0106.png', '30.00', '1', null, '2014-12-12 13:26:06', null);
INSERT INTO `t_dish` VALUES ('7', '2', '剁椒鱼头', '/02/0201.png', '20.00', '1', null, '2014-12-12 13:26:07', null);
INSERT INTO `t_dish` VALUES ('8', '2', '口水鸡', '/02/0202.png', '25.00', '1', null, '2014-12-12 13:26:08', null);
INSERT INTO `t_dish` VALUES ('9', '2', '四川咸烧白', '/02/0203.png', '30.00', '1', null, '2014-12-12 13:26:09', null);
INSERT INTO `t_dish` VALUES ('10', '2', '川椒大蒜肚条', '/02/0204.png', '20.00', '1', null, '2014-12-12 13:26:10', null);
INSERT INTO `t_dish` VALUES ('11', '2', '水煮牛肉', '/02/0205.png', '25.00', '1', null, '2014-12-12 13:26:11', null);
INSERT INTO `t_dish` VALUES ('12', '3', '宫保鸡丁', '/03/0301.png', '30.00', '1', null, '2014-12-12 13:26:12', null);
INSERT INTO `t_dish` VALUES ('13', '3', '支竹焖羊肉', '/03/0302.png', '20.00', '1', null, '2014-12-12 13:26:13', null);
INSERT INTO `t_dish` VALUES ('14', '3', '腊鸭腿煲仔饭', '/03/0303.png', '25.00', '1', null, '2014-12-12 13:26:14', null);
INSERT INTO `t_dish` VALUES ('15', '3', '菠萝咕咾肉', '/03/0304.png', '30.00', '1', null, '2014-12-12 13:26:15', null);
INSERT INTO `t_dish` VALUES ('16', '3', '菰米腊鸭腿煲仔饭', '/03/0305.png', '20.00', '1', null, '2014-12-12 13:26:16', null);
INSERT INTO `t_dish` VALUES ('17', '3', '蒜蓉粉丝蒸扇贝', '/03/0306.png', '25.00', '1', null, '2014-12-12 13:26:17', null);
INSERT INTO `t_dish` VALUES ('18', '3', '豉香蒸排骨', '/03/0307.png', '30.00', '1', null, '2014-12-12 13:26:18', null);
INSERT INTO `t_dish` VALUES ('19', '4', '宫保虾灯', '/04/0401.png', '20.00', '1', null, '2014-12-12 13:26:19', null);
INSERT INTO `t_dish` VALUES ('20', '4', '木耳炒白菜', '/04/0402.png', '25.00', '1', null, '2014-12-12 13:26:20', null);
INSERT INTO `t_dish` VALUES ('21', '4', '泡菜五花肉', '/04/0403.png', '30.00', '1', null, '2014-12-12 13:26:21', null);
INSERT INTO `t_dish` VALUES ('22', '4', '滑蛋虾仁', '/04/0404.png', '20.00', '1', null, '2014-12-12 13:26:22', null);
INSERT INTO `t_dish` VALUES ('23', '4', '软炸虾仁', '/04/0405.png', '25.00', '1', null, '2014-12-12 13:26:23', null);
INSERT INTO `t_dish` VALUES ('24', '4', '香醋花生', '/04/0406.png', '30.00', '1', null, '2014-12-12 13:26:24', null);
INSERT INTO `t_dish` VALUES ('25', '5', '上汤龙虾球', '/05/0501.png', '20.00', '1', null, '2014-12-12 13:26:25', null);
INSERT INTO `t_dish` VALUES ('26', '5', '文蛤炖蛋', '/05/0502.png', '25.00', '1', null, '2014-12-12 13:26:26', null);
INSERT INTO `t_dish` VALUES ('27', '5', '核桃虾仁', '/05/0503.png', '30.00', '1', null, '2014-12-12 13:26:27', null);
INSERT INTO `t_dish` VALUES ('28', '5', '白灼花蟹', '/05/0504.png', '20.00', '1', null, '2014-12-12 13:26:28', null);
INSERT INTO `t_dish` VALUES ('29', '5', '白灼虾', '/05/0505.png', '25.00', '1', null, '2014-12-12 13:26:29', null);
INSERT INTO `t_dish` VALUES ('30', '5', '龙井虾仁', '/05/0506.png', '30.00', '1', null, '2014-12-12 13:26:30', null);
INSERT INTO `t_dish` VALUES ('31', '6', '九江双蒸酒', '/06/0601.png', '20.00', '1', null, '2014-12-12 13:26:31', null);
INSERT INTO `t_dish` VALUES ('32', '6', '小糊涂神', '/06/0602.png', '25.00', '1', null, '2014-12-12 13:26:32', null);
INSERT INTO `t_dish` VALUES ('33', '6', '泸州老窖', '/06/0603.png', '30.00', '1', null, '2014-12-12 13:26:33', null);
INSERT INTO `t_dish` VALUES ('34', '6', '红秀天香', '/06/0604.png', '20.00', '1', null, '2014-12-12 13:26:34', null);
INSERT INTO `t_dish` VALUES ('35', '6', '贵州茅台酒', '/06/0605.png', '25.00', '1', null, '2014-12-12 13:26:35', null);
INSERT INTO `t_dish` VALUES ('36', '6', '长城干红葡萄酒', '/06/0606.png', '30.00', '1', null, '2014-12-12 13:26:36', null);
INSERT INTO `t_dish` VALUES ('37', '7', '冰红茶', '/07/0701.png', '20.00', '1', null, '2014-12-12 13:26:37', null);
INSERT INTO `t_dish` VALUES ('38', '7', '奶茶', '/07/0702.png', '25.00', '1', null, '2014-12-12 13:26:38', null);
INSERT INTO `t_dish` VALUES ('39', '7', '拿铁咖啡', '/07/0703.png', '30.00', '1', null, '2014-12-12 13:26:39', null);
INSERT INTO `t_dish` VALUES ('40', '7', '橙汁', '/07/0704.png', '20.00', '1', null, '2014-12-12 13:26:40', null);
INSERT INTO `t_dish` VALUES ('41', '7', '西瓜汁', '/07/0705.png', '25.00', '1', null, '2014-12-12 13:26:41', null);
INSERT INTO `t_dish` VALUES ('42', '7', '酸梅汤', '/07/0706.png', '30.00', '1', null, '2014-12-12 13:26:42', null);
INSERT INTO `t_dish` VALUES ('43', '8', '五仁月饼', '/08/0801.png', '20.00', '1', null, '2014-12-12 13:26:43', null);
INSERT INTO `t_dish` VALUES ('44', '8', '凉糕', '/08/0802.png', '25.00', '1', null, '2014-12-12 13:26:44', null);
INSERT INTO `t_dish` VALUES ('45', '8', '杯子蛋糕', '/08/0803.png', '30.00', '1', null, '2014-12-12 13:26:45', null);
INSERT INTO `t_dish` VALUES ('46', '8', '水果慕斯蛋糕', '/08/0804.png', '20.00', '1', null, '2014-12-12 13:26:46', null);
INSERT INTO `t_dish` VALUES ('47', '8', '蔓越莓小蛋糕', '/08/0805.png', '25.00', '1', null, '2014-12-12 13:26:47', null);
INSERT INTO `t_dish` VALUES ('48', '8', '香橙蛋糕卷', '/08/0806.png', '30.00', '1', null, '2014-12-12 13:26:48', null);
INSERT INTO `t_dish` VALUES ('49', '9', '南瓜饼', '/09/0901.png', '20.00', '1', null, '2014-12-12 13:26:49', null);
INSERT INTO `t_dish` VALUES ('50', '9', '热带海鲜炬饭', '/09/0902.png', '25.00', '1', null, '2014-12-12 13:26:50', null);
INSERT INTO `t_dish` VALUES ('51', '9', '紫薯花卷', '/09/0903.png', '30.00', '1', null, '2014-12-12 13:26:51', null);
INSERT INTO `t_dish` VALUES ('52', '9', '芋头腊味饭', '/09/0904.png', '20.00', '1', null, '2014-12-12 13:26:52', null);
INSERT INTO `t_dish` VALUES ('53', '9', '香煎土豆饼', '/09/0905.png', '25.00', '1', null, '2014-12-12 13:26:53', null);
INSERT INTO `t_dish` VALUES ('54', '9', '麻酱拌意面', '/09/0906.png', '30.00', '1', null, '2014-12-12 13:26:54', null);
INSERT INTO `t_dish` VALUES ('55', '10', '小鸡炖蘑菇', '/09/0901.png', '20.00', '1', null, '2014-12-12 13:26:49', null);
INSERT INTO `t_dish` VALUES ('56', '10', '番薯炖牛肉', '/09/0902.png', '25.00', '1', null, '2014-12-12 13:26:50', null);
INSERT INTO `t_dish` VALUES ('57', '10', '胡萝卜炖羊肉', '/09/0903.png', '30.00', '1', null, '2014-12-12 13:26:51', null);
INSERT INTO `t_dish` VALUES ('58', '10', '金针炖猪蹄', '/09/0904.png', '20.00', '1', null, '2014-12-12 13:26:52', null);
INSERT INTO `t_dish` VALUES ('59', '10', '鲜奶炖鸡汤', '/09/0905.png', '25.00', '1', null, '2014-12-12 13:26:53', null);

-- ----------------------------
-- Table structure for `t_function`
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `function_name` varchar(128) COLLATE utf8_bin NOT NULL,
  `function_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `function_picture` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `function_path` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `function_parent` bigint(20) DEFAULT NULL,
  `function_order` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `function_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_function_name` (`function_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES ('1', '权限管理', 'FunctionAdmin', 'icon01.png', '', '0', '010000', '1', '2014-12-12 13:26:01', null);
INSERT INTO `t_function` VALUES ('2', '权限管理', 'Function', null, '/function/function.action', '1', '010300', '1', '2014-12-12 13:26:01', null);
INSERT INTO `t_function` VALUES ('3', '角色管理', 'Role', null, '/role/role.action', '1', '010200', '1', '2014-12-12 13:26:01', null);
INSERT INTO `t_function` VALUES ('4', '用户管理', 'User', null, '/user/user.action', '1', '010100', '1', '2014-12-12 13:26:01', null);
INSERT INTO `t_function` VALUES ('5', '菜单菜品', 'DishAdmin', 'icon02.png', '/category/category.action', '0', '020000', '1', '2014-12-12 13:26:01', null);
INSERT INTO `t_function` VALUES ('6', '菜品分类管理', 'Category', null, '/category/category.action', '5', '020100', '1', '2014-12-12 13:26:01', null);
INSERT INTO `t_function` VALUES ('7', '菜品管理', 'Dish', null, '/dish/dish.action', '5', '020200', '1', '2014-12-12 13:26:01', null);
INSERT INTO `t_function` VALUES ('8', '收银流水', 'CashAdmin', 'icon03.png', '/cash/cash.action', '0', '20150420231158', '1', '2015-04-20 23:11:58', null);
INSERT INTO `t_function` VALUES ('9', '收银', 'Cash', null, '/cash/cash.action', '8', '20150420231221', '1', '2015-04-20 23:12:21', null);
INSERT INTO `t_function` VALUES ('10', '流水', 'Order', null, '/order/order.action', '8', '20150420231254', '1', '2015-04-20 23:12:54', null);
INSERT INTO `t_function` VALUES ('11', '会员', 'MemberAdmin', 'icon04.png', '/level/list.action', '0', '20150420232711', '1', '2015-04-20 23:27:11', null);
INSERT INTO `t_function` VALUES ('12', '会员等级', 'UserLevel', null, '/level/list.action', '11', '20150420232738', '1', '2015-04-20 23:27:38', null);
INSERT INTO `t_function` VALUES ('13', '用户会员', 'MemberShip', null, '/member/list.action', '11', '20150420232811', '1', '2015-04-20 23:28:11', null);

-- ----------------------------
-- Table structure for `t_member_ship`
-- ----------------------------
DROP TABLE IF EXISTS `t_member_ship`;
CREATE TABLE `t_member_ship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `level_id` bigint(20) NOT NULL,
  `member_id` varchar(20) NOT NULL,
  `current_score` int(20) DEFAULT '0',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_member_ship
-- ----------------------------
INSERT INTO `t_member_ship` VALUES ('1', '11', '1', '13988887770', '10000', '2015-04-21 01:05:24', null);

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_seq` varchar(128) NOT NULL,
  `table_number` int(8) NOT NULL,
  `attendee_number` int(8) NOT NULL,
  `total_price` float(64,2) DEFAULT NULL,
  `order_status` int(8) NOT NULL,
  `servent_id` bigint(20) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `casher_id` bigint(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `discount_price` double(64,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '2014120900001', '10', '4', '150.00', '102', '5', '11', '1', '2014-12-08 19:48:34', '2015-04-21 01:06:00', '120.00');
INSERT INTO `t_order` VALUES ('2', '2014120900002', '20', '2', '100.50', '100', '6', null, '9', '2014-12-09 20:48:44', '2015-04-21 00:01:08', '100.50');

-- ----------------------------
-- Table structure for `t_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `dish_id` bigint(20) NOT NULL,
  `dish_amount` int(8) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES ('1', '1', '1', '1', '2014-12-12 12:26:45', null);
INSERT INTO `t_order_item` VALUES ('2', '1', '2', '1', '2014-12-12 12:26:45', null);
INSERT INTO `t_order_item` VALUES ('3', '1', '3', '2', '2014-12-12 12:26:45', null);
INSERT INTO `t_order_item` VALUES ('4', '2', '1', '2', '2014-12-12 12:26:45', null);
INSERT INTO `t_order_item` VALUES ('5', '2', '2', '1', '2014-12-12 12:26:45', null);
INSERT INTO `t_order_item` VALUES ('6', '2', '5', '1', '2014-12-12 12:26:45', null);

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) COLLATE utf8_bin NOT NULL,
  `role_desc` varchar(128) COLLATE utf8_bin NOT NULL,
  `role_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '管理员', '1', '2014-12-12 12:26:45', '2014-12-16 08:24:15');
INSERT INTO `t_role` VALUES ('2', '菜单菜品管理员', '菜单菜品管理员', '1', '2014-12-12 12:26:45', null);
INSERT INTO `t_role` VALUES ('3', '收银流水管理员', '收银流水管理员', '1', '2015-04-20 11:30:19', null);
INSERT INTO `t_role` VALUES ('4', '点餐员', '点餐员', '1', '2015-04-20 11:30:52', null);
INSERT INTO `t_role` VALUES ('5', '收银员', '收银员', '1', '2015-04-20 11:31:08', null);
INSERT INTO `t_role` VALUES ('6', '会员管理员', '会员管理员', '1', '2015-04-20 11:31:24', null);
INSERT INTO `t_role` VALUES ('7', '流水查看', '流水查看员', '1', '2015-04-23 01:02:28', null);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role_function
-- ----------------------------
INSERT INTO `t_role_function` VALUES ('1', '1', '1', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('2', '1', '2', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('3', '1', '3', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('4', '1', '4', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('5', '2', '6', '2014-12-12 12:26:45', null);
INSERT INTO `t_role_function` VALUES ('6', '2', '5', '2014-12-12 12:26:45', null);
INSERT INTO `t_role_function` VALUES ('7', '2', '7', '2014-12-12 12:26:45', null);
INSERT INTO `t_role_function` VALUES ('8', '3', '8', null, null);
INSERT INTO `t_role_function` VALUES ('9', '3', '9', null, null);
INSERT INTO `t_role_function` VALUES ('10', '3', '10', null, null);
INSERT INTO `t_role_function` VALUES ('13', '5', '9', null, null);
INSERT INTO `t_role_function` VALUES ('14', '5', '8', null, null);
INSERT INTO `t_role_function` VALUES ('15', '5', '10', null, null);
INSERT INTO `t_role_function` VALUES ('16', '6', '11', null, null);
INSERT INTO `t_role_function` VALUES ('17', '6', '12', null, null);
INSERT INTO `t_role_function` VALUES ('18', '6', '13', null, null);
INSERT INTO `t_role_function` VALUES ('19', '7', '8', null, null);
INSERT INTO `t_role_function` VALUES ('20', '7', '10', null, null);
INSERT INTO `t_role_function` VALUES ('21', '4', '8', null, null);
INSERT INTO `t_role_function` VALUES ('22', '4', '10', null, null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(256) COLLATE utf8_bin NOT NULL,
  `cellphone` varchar(16) COLLATE utf8_bin NOT NULL,
  `user_score` bigint(20) DEFAULT '0',
  `user_status` tinyint(1) DEFAULT '1',
  `user_level_id` bigint(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_username` (`username`),
  KEY `index_cellphone` (`cellphone`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'ceb4f32325eda6142bd65215f4c0f371', '13912345671', null, '1', '1', '2012-09-29 00:00:00', null);
INSERT INTO `t_user` VALUES ('2', 'dish', 'f5f1a7cb292def2c144cf671eb57c169', '13988887771', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('3', 'cash', '8800eae74ab9da2e561dae4fa1460adf', '13988887772', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('4', 'member', 'e1f7eac852cdeff0e4b20546e1e16492', '13988887773', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('5', '点餐员A', 'bc6c733ccd5926afcb8d67874d72f795', '13988887774', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('6', '点餐员B', 'f0ae104c748ecdace1af536d4bceb4bd', '13988887775', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('7', '点餐员C', 'ba709db84ffdbe6c10fcb77989bb1ffd', '13988887776', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('8', '收银员A', '78a654f440ee8d8a0b635f1d93b3380e', '13988887777', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('9', '收银员B', 'f9644cb34c3e9d5f18cbca4b69d9ceba', '13988887778', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('10', '收银员C', '5c614e7668e147dd9af7e6548afe175d', '13988887779', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('11', '13988887770', 'fa4c7e236ff8f888fb61be6bcb9421af', '13988887770', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('12', '12345', '745966536bed050935a5adbe3dd378e1', '13988887733', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('13', '1234', 'ba8595896ca2c2866f385fc10903bd29', '13988887712', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('14', '1212', '91b1e9564884bd04a50eac1c70c86e59', '13988887721', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('15', '12121', 'fadadb7b33a483d0bc01ea81e8b604cd', '13988887710', '0', '1', null, null, null);
INSERT INTO `t_user` VALUES ('16', 'abcd', '3e8c66c245c9cf2769741a3bb2264776', '13945674567', '0', '1', null, null, null);

-- ----------------------------
-- Table structure for `t_user_level`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_level`;
CREATE TABLE `t_user_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level_name` varchar(128) NOT NULL,
  `discount` float(32,2) DEFAULT '10.00',
  `level_score` int(20) DEFAULT '0',
  `level_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_level
-- ----------------------------
INSERT INTO `t_user_level` VALUES ('1', '钻石会员', '8.00', '10000', '1', '2014-12-12 12:26:45', null);
INSERT INTO `t_user_level` VALUES ('2', '白金会员', '8.50', '7500', '1', '2014-12-12 12:26:45', null);
INSERT INTO `t_user_level` VALUES ('3', '金牌会员', '9.00', '5000', '1', '2014-12-12 12:26:45', null);
INSERT INTO `t_user_level` VALUES ('4', '普通会员', '9.50', '0', '1', '2014-12-12 12:26:45', null);
INSERT INTO `t_user_level` VALUES ('5', '非会员', '10.00', '0', '1', '2014-12-12 12:26:45', null);

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('3', '1', '2', null, null);
INSERT INTO `t_user_role` VALUES ('4', '1', '3', null, null);
INSERT INTO `t_user_role` VALUES ('5', '1', '1', null, null);
INSERT INTO `t_user_role` VALUES ('6', '1', '6', null, null);
INSERT INTO `t_user_role` VALUES ('7', '2', '2', null, null);
INSERT INTO `t_user_role` VALUES ('8', '3', '3', null, null);
INSERT INTO `t_user_role` VALUES ('9', '4', '6', null, null);
INSERT INTO `t_user_role` VALUES ('10', '5', '4', null, null);
INSERT INTO `t_user_role` VALUES ('11', '6', '4', null, null);
INSERT INTO `t_user_role` VALUES ('12', '7', '4', null, null);
INSERT INTO `t_user_role` VALUES ('13', '8', '5', null, null);
INSERT INTO `t_user_role` VALUES ('14', '9', '5', null, null);
INSERT INTO `t_user_role` VALUES ('15', '10', '5', null, null);
INSERT INTO `t_user_role` VALUES ('16', '11', '1', null, null);
INSERT INTO `t_user_role` VALUES ('17', '12', '1', null, null);
INSERT INTO `t_user_role` VALUES ('18', '13', '2', null, null);
INSERT INTO `t_user_role` VALUES ('19', '13', '6', null, null);
INSERT INTO `t_user_role` VALUES ('20', '13', '5', null, null);
INSERT INTO `t_user_role` VALUES ('21', '13', '4', null, null);
INSERT INTO `t_user_role` VALUES ('22', '13', '3', null, null);
INSERT INTO `t_user_role` VALUES ('24', '14', '4', null, null);
INSERT INTO `t_user_role` VALUES ('25', '15', '4', null, null);
INSERT INTO `t_user_role` VALUES ('26', '16', '4', null, null);
