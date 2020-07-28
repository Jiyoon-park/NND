CREATE TABLE `member` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `name` char(8) NOT NULL,
  `email` varchar(128) DEFAULT NULL,
  `profile` varchar(128) DEFAULT NULL,
  `company` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;