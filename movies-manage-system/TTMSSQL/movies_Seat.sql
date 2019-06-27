create table movies_Seat(
    ID int not null comment '分片算法规则',
    movieSeat_Id bigint not null primary key auto_increment comment '座位的自增主键',
    movieSeat_HallId bigint not null comment '座位关联的影厅',
    movieSeat_IsBuy int not null comment '座位是否购买使用位运算 每一位代表一场演出计划的购买状态',
    movieSeat_IsOk int not null comment '电影是否为好的',
    movieSeat_State int not null comment '座位的状态',
    movieSeat_X int not null comment '座位的横坐标',
    movieSeat_Y int not null comment '座位的纵坐标'
)charset=utf8;
