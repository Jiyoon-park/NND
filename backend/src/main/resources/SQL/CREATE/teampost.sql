 CREATE TABLE `teampost` (  
  `teampostno` int(10) NOT NULL AUTO_INCREMENT,
  `teamboardno` int NOT NULL, 
  `title` varchar(128) NOT NULL,
  `content` varchar(500) NOT NULL,
  `createdate` varchar(128) NOT NULL,
  `notice` tinyint(1) not null,
  PRIMARY KEY (`teampostno`),
  FOREIGN KEY(`teamboardno`) REFERENCES teamboard(`teamboardno`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;