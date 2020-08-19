 CREATE TABLE `teampost` (  
  `teampostno` int(10) NOT NULL AUTO_INCREMENT,
  `teamboardno` int NOT NULL, 
  `memberidx` int not null,
  `writer` varchar(30) NOT NULL,
  `title` varchar(128) NOT NULL,
  `content` varchar(500) NOT NULL,
   `createdate` datetime DEFAULT current_timestamp(),,
  `notice` tinyint(1) not null,
  `latitude` varchar(128) default NULL,
  'longitude` varchar(128) default NULL,
  PRIMARY KEY (`teampostno`),
  FOREIGN KEY(`teamboardno`) REFERENCES teamboard(`teamboardno`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(`memberidx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
