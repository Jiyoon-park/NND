CREATE TABLE `letter` (
  `letterno` int(10) NOT NULL AUTO_INCREMENT,
  `sendidx` int NOT NULL,
  `receiveidx` int NOT NULL,
  `content` varchar(500) DEFAULT NULL,
  `createdate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`letterno`),
  FOREIGN KEY (`sendidx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`receiveidx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;