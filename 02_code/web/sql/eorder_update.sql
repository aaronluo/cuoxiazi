ALTER TABLE `t_order` ADD `discount_price` float(64,2) DEFAULT NULL;
UPDATE `t_order` SET `discount_price`=`total_price` WHERE `id` > 0;

DROP TABLE IF EXISTS `t_member_ship`;
CREATE TABLE `t_member_ship` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `user_id` int(32) NOT NULL,
  `level_id` int(32) NOT NULL,
  `member_id` varchar(20) NOT NULL,
  `current_score` int(32) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;