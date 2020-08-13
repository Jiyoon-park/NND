 CREATE TABLE `teamdiary` (  
  `teamdiaryno` int(10) NOT NULL AUTO_INCREMENT,
  `teamboardno` int DEFAULT NULL, 
  `title` varchar(128) NOT NULL,
 `color` varchar(128) NOT NULL,
 `start` varchar(128) NOT NULL,
 `end` varchar(128) NOT NULL,
 `timed` tinyint(1) not null,
  PRIMARY KEY (`teamdiaryno`),
  FOREIGN KEY(`teamboardno`) REFERENCES teamboard(`teamboardno`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;