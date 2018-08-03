CREATE TABLE blogz.usertab
(
  id INT PRIMARY KEY NOT NULL COMMENT 'id' AUTO_INCREMENT,
  username VARCHAR(50) DEFAULT '' NOT NULL COMMENT '用户名',
  password VARCHAR(200) DEFAULT '' NOT NULL COMMENT '密码',
  email VARCHAR(100) DEFAULT '' NOT NULL COMMENT '邮箱',
  nickname VARCHAR(200) COMMENT '昵称',
  telnum VARCHAR(100) COMMENT '电话号码',
  sex VARCHAR(50) COMMENT '性别'
);

INSERT INTO blogz.usertab (username, password, email, nickname, telnum, sex) VALUES ('test', '111', 'test@test.com', 'testmomo', '1357777777', '1');