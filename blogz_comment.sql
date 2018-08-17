INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '测试评论001', '1', '0', '2018-08-14 10:09:21', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '测试评论002 1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', '2', '1', '2018-08-14 10:09:21', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, 'test003', '3', '0', '2018-08-14 10:09:21', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '测试评论啦啦啦', '4', '0', '2018-08-14 10:09:21', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '1aqqqqqq', '2', '0', '2018-08-14 15:19:27', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '111111111111111111111最大值内的数据即可这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可,这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可,这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可这是详细内容概要，能写多少写多少，然后给定一个最大值，只显示最大值内的数据即可
111111111111111111111111111111111', '1', '0', '2018-08-14 15:38:54', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '111', '1', '1', '2018-08-14 15:39:47', 2);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222', '1', '1', '2018-08-14 15:40:40', 2);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (2, 'asdfsafd', '2', '1', '2018-08-14 16:38:38', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '1231231231323', '2', '1', '2018-08-15 11:26:47', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '我来留言了', '2', '1', '2018-08-15 11:27:23', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '再来一条', '2', '1', '2018-08-15 11:28:33', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '回复留言1', '2', '1', '2018-08-15 13:56:46', 12);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '再评论一条试试看！', '1', '1', '2018-08-15 14:25:23', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '回复留言2', '2', '1', '2018-08-15 16:06:57', 12);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '123', '2', '1', '2018-08-15 17:27:49', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (1, '456', '2', '1', '2018-08-15 17:32:22', 16);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (2, '地方大幅度发', '2', '1', '2018-08-15 17:35:16', 9);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (2, '你好 这里浏览', '2', '1', '2018-08-15 17:35:33', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (38, '写得不错，继续努力!', '5', '1', '2018-08-17 15:57:35', 0);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (38, '感谢！', '5', '1', '2018-08-17 15:57:47', 20);
INSERT INTO blogz.comment (paperid, content, pubid, flag, pubtime, replyid) VALUES (2, '我来评论了！', '5', '1', '2018-08-17 15:58:29', 0);

create table comment
(
  id INT(10) auto_increment comment 'id'
    primary key,
  paperid INT(10) not null comment '对应文章的id',
  content VARCHAR(3000) null comment '评论内容',
  pubid VARCHAR(300) null comment '评论者id',
  flag VARCHAR(10) default '1' null,
  pubtime TIMESTAMP(19) default CURRENT_TIMESTAMP null,
  replyid INT(10) default 0 null comment '回复评论id'
)
;