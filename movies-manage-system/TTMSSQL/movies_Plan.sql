create table movies_Plan(
    ID bigint not null comment '分片算法规则',
    moviePlan_Id bigint not null primary key auto_increment comment '电影计划的主键',
    moviePlan_MovieId bigint not null comment '电影计划绑定的电影编号',
    moviePlan_HallId bigint not null comment '电影计划绑定的影厅编号',
    moviePlan_StartTime bigint not null comment '电影计划的播放时间',
    moviePlan_State int not null default 0 comment '电影计划的状态',
    moviePlan_Price double(5,2) comment '电影计划价钱',
    moviePlan_Number int not null comment '当天电影的第几场,至关重要 用此得到座位的信息'
)charset=utf8
