CREATE TABLE `projecthistory`(
  `historyno` int not null auto_increment,
  `idx` int NOT NULL default 0,
  `projectname` varchar(128) default null,
  `content` varchar(500) default null,
  `gitlink` varchar(128) default null,
  `usedstack` varchar(200) default null,
  PRIMARY KEY (`historyno`),
  FOREIGN KEY (`idx`) REFERENCES member(`idx`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;