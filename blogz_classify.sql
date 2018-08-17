INSERT INTO blogz.classify (name, icon) VALUES ('技术', 'icon-star');
INSERT INTO blogz.classify (name, icon) VALUES ('分享', 'icon-share');
INSERT INTO blogz.classify (name, icon) VALUES ('杂文', 'icon-camera');

create table classify
(
  id INT(10) auto_increment comment 'id'
    primary key,
  name VARCHAR(50) default '' not null comment '分类名称',
  icon VARCHAR(50) null
)
;

create unique index classify_name_uindex
  on classify (name)
;