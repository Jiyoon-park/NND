 CREATE TABLE `memberboard` (  
  `boardno` int(10) NOT NULL AUTO_INCREMENT,
  `idx` int DEFAULT NULL, 
  `email` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `title` varchar(128) NOT NULL,
  `content` varchar(255) NOT NULL,
  `imageurl` varchar(300) NOT NULL,
  `contentstack` varchar(128) NOT NULL,
  `techstack` varchar(128) NOT NULL, 
  `createdate` datetime DEFAULT current_timestamp(),
  `likecnt` int(10) NOT NULL,
  PRIMARY KEY (`boardno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;