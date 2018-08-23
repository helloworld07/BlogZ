-- auto-generated definition
create table usertrack
(
  id INT(10) auto_increment comment 'id'
    primary key,
  userid INT(10) default 0 not null comment '用户id',
  classname VARCHAR(500) null comment '操作类名',
  operation VARCHAR(1000) null comment '操作',
  url VARCHAR(500) null,
  time TIMESTAMP(19) default CURRENT_TIMESTAMP null comment '操作时间',
  ipadd VARCHAR(100) null,
  args VARCHAR(500) null
)
;
