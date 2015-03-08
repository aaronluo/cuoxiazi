ALTER TABLE `t_order` ADD `discount_price` float(64,2) DEFAULT NULL;
UPDATE `t_order` SET `discount_price`=`total_price` WHERE `id` > 0;