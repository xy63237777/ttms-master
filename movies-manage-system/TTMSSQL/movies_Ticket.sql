create table movies_Ticket(
	ID varchar(255) not null comment '分片算法规则',
	moviesTicket_Id bigint not null primary key comment '电影票主键',
	moviesTicket_UserId varchar(255) not null comment '绑定的用户',
	moviesTicket_SeatId bigint not null comment '绑定的座位编号',
	moviesTicket_Price double(5,2) not null comment '电影价钱',
	moviesTicket_HallId bigint not null comment '电影厅编号',
	moviesTicket_Time bigint not null comment '电影票开始时间',
	moviesTicket_MovieName varchar(255) not null comment '电影的名字',
	moviesTicket_State int not null default 0 comment '电影票的状态',
	moviesTicket_Code varchar(255) not null comment '取票码'
)charset=utf8;
