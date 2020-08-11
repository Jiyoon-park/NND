CREATE TABLE `image` (
  `imageno` int NOT NULL AUTO_INCREMENT,
  `boardno` int NOT NULL,
  `url` varchar(300) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`imageno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;