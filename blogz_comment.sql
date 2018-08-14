-- auto-generated definition
create table comment
(
  id INT(10) auto_increment comment 'id'
    primary key,
  paperid INT(10) default  not null comment '对应文章的id',
  content VARCHAR(3000) null comment '评论内容',
  pubid VARCHAR(300) null comment '评论者id'
)
;