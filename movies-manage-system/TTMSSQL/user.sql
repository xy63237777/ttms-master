create table users(
	ID varchar(255) not null,
	user_Email varchar(255) not null primary key comment '用户的邮箱',
	user_Name varchar(255) not null comment '用户的名字',
	user_Password varchar(255) not null comment '用户的密码',
	user_ImageUrl varchar(255) not null comment '用户的头像地址',
	user_State int not null default 0 comment '用户的状态 ',
	user_CreateTime timestamp not null comment '用户的创建时间',
	user_LastLoginTime timestamp comment '用户的上次登录时间',
	user_LoginCount bigint comment '用户的登录次数',
	user_Authority int not null default 16 comment '用户的权限采用位运算'
)charset=utf8;

insert into users(ID,user_Email, user_Name, user_Password,
                          user_ImageUrl, user_CreateTime, user_LastLoginTime,
                          user_LoginCount, user_Authority,user_State) values('123','123','刘亦菲','$2a$10$uI05xEjnZl4225pHTVmo1e/Jpka7ADimlkStG0.KxY1.e8iSYX8xK','123',0,now(),now(),0,16);


insert into users(ID,user_Email, user_Name, user_Password,
						                            user_ImageUrl, user_CreateTime, user_LastLoginTime,
						                            user_LoginCount, user_Authority,user_State) values('123456','123456','刘亦菲','$2a$10$uI05xEjnZl4225pHTVmo1e/Jpka7ADimlkStG0.KxY1.e8iSYX8xK','123',0,now(),now(),0,31);
