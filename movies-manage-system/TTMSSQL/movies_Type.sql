create table movies_Type(
	ID varchar(255) not null comment '分片算法规则',
	movieType_Id bigint not null primary key auto_increment comment '自增主键',
	movieType_Name varchar(255) not null comment '电影类型的名字',
	movieType_State int not null default 0 comment '电影类型的状态',
	movieType_Bit int not null unique comment '电影类型的位数(相当重要)'
)charset=utf8;

insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('爱情',null,'爱情',0,0);
insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('喜剧',null, '喜剧',0,1);
insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('动作',null, '动作',0,2);
insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('剧情',null, '剧情',0,3);
insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('科幻',null, '科幻',0,4);
insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('恐怖',null, '恐怖',0,5);
insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('动画',null, '动画',0,6);
insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('惊悚',null, '惊悚',0,7);
insert into movies_Type(ID,movieType_Id,movieType_Name,movieType_State,movieType_Bit) values('犯罪',null, '犯罪',0,8);
