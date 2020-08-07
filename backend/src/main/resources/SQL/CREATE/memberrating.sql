 CREATE TABLE `memberrating` (  
  `ratingno` int(10) NOT NULL AUTO_INCREMENT,
  `idx` int NOT NULL, 
  `commitcnt` int Default 0,
  `issuecnt` int default 0,
  `attendrate` int default 0,
  `createdate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ratingno`),
  FOREIGN KEY(`idx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;