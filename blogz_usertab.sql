INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('test', '111', 'test@test.com', 'testmomo', '1357777777', '1', '1');
INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('admin', '123', 'admin@zcy.com', 'admin', '13777777777', '1', '1');
INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('guest001', 'qqq111222333', '123@123.com', '001', '13838384381', '0', '1');
INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('guest002', '222222222', '22@ww.com', '002', '13522222222', '1', '1');
INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('guest007', '1', '123@qq.com', '007guest', '131313374569', '1', '1');

create table usertab
(
  id INT(10) auto_increment comment 'id'
    primary key,
  username VARCHAR(50) default '' not null comment '用户名',
  password VARCHAR(200) default '' not null comment '密码',
  email VARCHAR(100) default '' not null comment '邮箱',
  nickname VARCHAR(200) null comment '昵称',
  telnum VARCHAR(100) null comment '电话号码',
  sex VARCHAR(50) null comment '性别',
  pub VARCHAR(10) default '1' null
)
;

create unique index usertab_username_uindex
  on usertab (username)
;
