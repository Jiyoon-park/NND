CREATE TABLE `memberboard` (
  `boardno` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) DEFAULT NULL,
  `title` char(128) NOT NULL,
  `content` char(255) NOT NULL,
  `contentstack` char(128) NOT NULL,
  `techstack` char(128) NOT NULL,
  `createdate` datetime DEFAULT current_timestamp(),
  `likecnt` int(10) NOT NULL,
  PRIMARY KEY (`boardno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;