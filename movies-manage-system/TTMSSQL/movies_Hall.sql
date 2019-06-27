create table movies_Hall(
    ID varchar(255) not null comment '分片算法规则',
    movieHall_Id bigint not null primary key auto_increment comment '影厅自增主键',
    movieHall_Name varchar(255) not null comment '影厅的名字',
    movieHall_State int not null default 0 comment '影厅的状态',
    movieHall_Row int not null default 0 comment '影厅的行',
    movieHall_Col int not null default 0 comment '影厅的列'
)charset=utf8;

insert into movies_Hall(ID,movieHall_Name,movieHall_State,movieHall_Row,movieHall_Col)
    values(null,'123',0,3,3);
