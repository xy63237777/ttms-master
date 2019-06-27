create table ttms_nowtime(
    ID varchar(255),
    name varchar(255) primary key comment '主键',
    endTime bigint not null comment '结束时间'
)charset=utf8;

insert into ttms_nowtime(ID,endTime) values('master',now());
