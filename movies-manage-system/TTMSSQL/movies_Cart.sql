create table movies_Cart(
	ID varchar(255) not null comment '分片算法规则',
	movies_CartId bigint not null primary key AUTO_INCREMENT comment '主键',
	movies_CartCount tinyint not null comment '电影的数量 ',
	movies_Code varchar(255) not null comment '取票码',
	movies_CodeImage varchar(255) not null comment '二维码地址',
	movies_Seats varchar(255) not null comment '座位信息',
	movies_Price double not null comment '总价钱',
	movies_MovieName varchar(255) not null comment '绑定的电影名字 ',
	movies_HallName varchar(255) not null comment '电影厅的名字',
	movies_UserId varchar(255) not null comment '用户的id',
	movies_Status tinyint not null default 0 comment '电影票状态',
	movies_CartCreateTime timestamp not null comment '电影票创建时间',
	movies_PlanId bigint not null
)charset=utf8;
