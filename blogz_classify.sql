INSERT INTO blogz.classify (name, icon) VALUES ('技术', 'icon-star');
INSERT INTO blogz.classify (name, icon) VALUES ('分享', 'icon-share');
INSERT INTO blogz.classify (name, icon) VALUES ('杂文', 'icon-camera');

CREATE TABLE blogz.classify
(
  id INT PRIMARY KEY NOT NULL COMMENT 'id' AUTO_INCREMENT,
  name VARCHAR(50) DEFAULT '' NOT NULL COMMENT '分类名称',
  icon VARCHAR(50)
);
CREATE UNIQUE INDEX classify_name_uindex ON blogz.classify (name);