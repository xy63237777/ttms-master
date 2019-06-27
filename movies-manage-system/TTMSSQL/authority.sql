create table authority(
	ID varchar(255) not null comment '分片算法规则',
	authority_Id bigint NOT NULL primary key AUTO_INCREMENT,
	authority_Name varchar(255) not null comment '权限的中文名字',
	authority_SimpleName varchar(255) not null comment '权限',
	authority_Bit tinyint not null unique comment '权限的位操作',
	authority_State tinyint not null default 0 comment '权限的状态',
	authority_UpdateTime timestamp not null comment '最后一次更新时间',
)charset=utf8;

insert into authority(ID,authority_Name,authority_SimpleName,authority_Bit
,authority_State,authority_UpdateTime) values(null,'超级管理员','root',0,0,now());

insert into authority(ID,authority_Name,authority_SimpleName,authority_Bit
,authority_State,authority_UpdateTime) values(null,'管理员','admin',1,0,now());

insert into authority(ID,authority_Name,authority_SimpleName,authority_Bit
,authority_State,authority_UpdateTime) values(null,'影厅管理员','hallAdmin',2,0,now());

insert into authority(ID,authority_Name,authority_SimpleName,authority_Bit
,authority_State,authority_UpdateTime) values(null,'会员用户','VIP',3,0,now());

insert into authority(ID,authority_Name,authority_SimpleName,authority_Bit
,authority_State,authority_UpdateTime) values(null,'普通用户','user',4,0,now());
