CREATE TABLE `member` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `name` char(8) NOT NULL,
  `email` varchar(128) NOT NULL,
  `profile` varchar(128) default NULL,
  `password` varchar(300) default null,
  `company` varchar(10) default NULL,
  `gitaddr` varchar(128) Default null,
  `memberstack` varchar(500) default null,
 `teamboardno` int(10) default 0,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
