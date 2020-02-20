CREATE TABLE `t_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) NOT NULL DEFAULT '' COMMENT '优惠券码',
  `pic_url` varchar(255) NOT NULL DEFAULT '' COMMENT '优惠券图',
  `achieve_amount` int(255) NOT NULL DEFAULT 0 COMMENT '达到满减资格金额',
  `reduce_amount` int(11) NOT NULL DEFAULT 0 COMMENT '所减金额',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存,当库存为0不可领取',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '优惠券名称',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态为0表示可用,1为不可用',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT '优惠券定义表';