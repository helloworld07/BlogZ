INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('test', '111', 'test@test.com', 'testmomo', '1357777777', '1', '1');
INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('admin', '123', 'admin@zcy.com', 'admin', '13777777777', '1', '1');
INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('guest001', 'qqq111222333', '123@123.com', '001', '13838384381', '0', '1');
INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('guest002', '222222222', '22@ww.com', '002', '13522222222', '1', '1');
INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex, pub) VALUES ('guest007', '1', '123@qq.com', '007guest', '131313374569', '1', '1');

CREATE TABLE blogz.usertab
(
  id INT PRIMARY KEY NOT NULL COMMENT 'id' AUTO_INCREMENT,
  username VARCHAR(50) DEFAULT '' NOT NULL COMMENT '用户名',
  password VARCHAR(200) DEFAULT '' NOT NULL COMMENT '密码',
  email VARCHAR(100) DEFAULT '' NOT NULL COMMENT '邮箱',
  nickname VARCHAR(200) COMMENT '昵称',
  telnum VARCHAR(100) COMMENT '电话号码',
  sex VARCHAR(50) COMMENT '性别',
  pub VARCHAR(10) DEFAULT '1' COMMENT '是否公开',
  collids VARCHAR(1000) DEFAULT '0,' NOT NULL COMMENT '收藏文章ids'
);
CREATE UNIQUE INDEX usertab_username_uindex ON blogz.usertab (username);
