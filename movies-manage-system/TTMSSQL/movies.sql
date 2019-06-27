create table movies(
    ID varchar(255) not null comment '分片算法规则',
    movie_Id bigint not null primary key auto_increment comment'电影主键',
    movie_Name varchar(255) not null comment '电影的名字',
    movie_State int not null default 0 comment '电影的状态',
	movie_DefaultImage varchar(255) comment '电影默认的图片',
    movie_DetailImage varchar(255) comment '电影详情的图片',
    movie_BeginTime bigint comment '电影的上映时间',
    movie_Author varchar(255)comment '电影作者',
    movie_ScriptWriter varchar(255) comment '电影编剧',
    movie_Actor varchar(255) comment '电影演员',
	movie_Country varchar(255) comment '电影国家',
	movie_Language varchar(255) comment '电影的语言',
	movie_Type int not null default 0 comment '电影类型用位运算操作',
	movie_Length int not null comment '电影时长',
	movie_SalesVolume bigint not null default 0 comment '电影的销量',
    movie_Grade double(3,1) not null default 10.0 comment '电影的评分'
)charset=utf8;

insert into movies(movie_Name,movie_BeginTime) values('加勒比海盗',123);
insert into movies(movie_Name,movie_BeginTime,movie_Type) values('活佛济公',123,8);
insert into movies(movie_Name,movie_BeginTime,movie_Type) values('哈利波特',123,1<<4);
insert into movies(movie_Name,movie_BeginTime,movie_Type) values('霍比特人',123,7);

insert into movies(ID,movie_Id, movie_Name, movie_State, movie_DefaultImage,movie_DetailImage, movie_BeginTime, movie_Author, movie_ScriptWriter, movie_Actor, movie_Country,movie_Language, movie_Type, movie_Length, movie_SalesVolume, movie_Grade)
    values ('指环王', null,'指环王',0,'123','123',now(),'123','123','123','123','123',  33,120,0,10.0)

	

