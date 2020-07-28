CREATE TABLE `teamboard` (
  `teamboardno` int(10) NOT NULL AUTO_INCREMENT,
  `idx` int default NULL,  
  `email` varchar(128) NOT NULL,
  `teamname` varchar(128) NOT NULL,
  `membernames` varchar(200) default null,
  `groupsize` int(10) NOT NULL,
  `deadline` varchar(30) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(500) NOT NULL,
  `techstack` varchar(300) DEFAULT NULL,
  `contentstack` varchar(300) DEFAULT NULL,
  `likecnt` int (10) DEFAULT 0,
  `createdate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`teamboardno`),
  FOREIGN KEY(`idx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;