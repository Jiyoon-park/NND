CREATE TABLE `teamboard` (
  `teamboardno` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `teamname` varchar(128) NOT NULL,
  `groupsize` int(10) NOT NULL,
  `deadline` varchar(30) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(500) NOT NULL,
  `techstack` varchar(300) DEFAULT NULL,
  `contentstack` varchar(300) DEFAULT NULL,
  `likecnt` int (10) DEFAULT 0,
  `createdate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`teamboardno`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;