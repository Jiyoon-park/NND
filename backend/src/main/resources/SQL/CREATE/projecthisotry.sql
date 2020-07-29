CREATE TABLE `projecthistory`(
  `idx` int not null AUTO_INCREMENT,
  `projectname` varchar(128) default null,
  `content` varchar(500) default null,
  `gitlink` varchar(128) default null,
  `usedstack` varchar(200) default null,
  PRIMARY KEY (`idx`),
  FOREIGN KEY (`idx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;