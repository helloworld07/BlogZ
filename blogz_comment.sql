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

INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (1, '测试评论001', '1', '1', '2018-08-14 10:09:21');
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (1, '测试评论002', '2', '1', '2018-08-14 10:09:21');
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (1, 'test003', '3', '1', '2018-08-14 10:09:21');
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (1, '测试评论啦啦啦', '4', '1', '2018-08-14 10:09:21');
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (1, '1aqqqqqq', '2', '1', '2018-08-14 15:19:27');
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (1, '111111111111111111111最大值内的数据即可这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可,这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可,这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可
111111111111111111111111111111111', '1', '1', '2018-08-14 15:38:54');
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (1, '111', '1', '1', '2018-08-14 15:39:47');
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (1, '2', '1', '1', '2018-08-14 15:40:40');
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime) VALUES (2, 'asdfsafd', '2', '1', '2018-08-14 16:38:38');

