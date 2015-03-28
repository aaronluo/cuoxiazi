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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `t_category` VALUES ('11', '默认', null, '2014-12-12 12:26:53', null);

-- ----------------------------
-- Table structure for `t_dish`
-- ----------------------------
DROP TABLE IF EXISTS `t_dish`;
CREATE TABLE `t_dish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL,
  `dish_name` varchar(128) NOT NULL,
  `dish_picture` varchar(256) DEFAULT NULL,
  `dish_price` float(64,0) NOT NULL,
  `on_sell` tinyint(1) DEFAULT NULL,
  `misc` varchar(128) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `function_name` varchar(128) NOT NULL,
  `function_desc` varchar(128) DEFAULT NULL,
  `function_picture` varchar(128) DEFAULT NULL,
  `function_path` varchar(128) DEFAULT NULL,
  `function_parent` bigint(20) DEFAULT NULL,
  `function_order` varchar(16) DEFAULT NULL,
  `function_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES ('1', '权限管理', 'Administration', 'icon01.png', '', '0', '010000', '1', null, null);
INSERT INTO `t_function` VALUES ('2', '权限管理', 'Function', null, '/function/function.action', '1', '010300', '1', null, null);
INSERT INTO `t_function` VALUES ('3', '角色管理', 'Role', null, '/role/role.action', '1', '010200', '1', null, null);
INSERT INTO `t_function` VALUES ('4', '用户管理', 'User', null, '/user/user.action', '1', '010100', '1', null, null);

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
  `order_status` bigint(20) NOT NULL,
  `servent_id` bigint(20) NOT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `casher_id` bigint(20) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `discount_price` float(64,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `dish_id` bigint(20) NOT NULL,
  `dish_amount` bigint(20) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `role_name` varchar(128) NOT NULL,
  `role_desc` varchar(128) NOT NULL,
  `role_status` tinyint(1) DEFAULT '1',
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '管理员', '1', null, '2014-12-16 08:24:15');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role_function
-- ----------------------------
INSERT INTO `t_role_function` VALUES ('1', '1', '1', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('2', '1', '2', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('3', '1', '3', '2014-12-16 08:24:15', null);
INSERT INTO `t_role_function` VALUES ('4', '1', '4', '2014-12-16 08:24:15', null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(256) NOT NULL,
  `cellphone` varchar(16) NOT NULL,
  `user_score` bigint(20) DEFAULT NULL,
  `user_status` tinyint(1) DEFAULT '1',
  `user_level_id` bigint(20) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'ceb4f32325eda6142bd65215f4c0f371', '13912345671', null, '1', '1', '2012-09-29 00:00:00', null);
INSERT INTO `t_user` VALUES ('2', 'test', '889255f1c9c8f12a353be255f78a848b', '13912345672', null, '1', '2', '2012-09-29 00:00:00', '2014-12-14 10:45:54');

-- ----------------------------
-- Table structure for `t_user_level`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_level`;
CREATE TABLE `t_user_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level_name` varchar(128) NOT NULL,
  `discount` float(32,2) NOT NULL,
  `level_score` bigint(20) NOT NULL,
  `level_status` tinyint(1) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_level
-- ----------------------------
INSERT INTO `t_user_level` VALUES ('1', '钻石会员', '8.00', '10000', '1', null, null);
INSERT INTO `t_user_level` VALUES ('2', '白金会员', '8.50', '7500', '1', null, null);
INSERT INTO `t_user_level` VALUES ('3', '金牌会员', '9.00', '5000', '1', null, null);
INSERT INTO `t_user_level` VALUES ('4', '普通会员', '9.50', '0', '1', null, null);
INSERT INTO `t_user_level` VALUES ('5', '非会员', '10.00', '0', '1', null, null);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1', null, null);

DROP TABLE IF EXISTS `t_member_ship`;
CREATE TABLE `t_member_ship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `level_id` bigint(20) NOT NULL,
  `member_id` varchar(20) NOT NULL,
  `current_score` bigint(20) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `t_member_ship` VALUES ('1', '1', '1','13912345671', 0, null, null);

-- add index
ALTER TABLE `t_function` ADD INDEX index_function_name ( `function_name` );
ALTER TABLE `t_role` ADD INDEX index_role_name ( `role_name` );
ALTER TABLE `t_user` ADD INDEX index_username ( `username` );
ALTER TABLE `t_user` ADD INDEX index_cellphone ( `cellphone` );