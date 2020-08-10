 CREATE TABLE `teamnotice` (  
  `teamnoticeno` int(10) NOT NULL AUTO_INCREMENT,
  `teamboardno` int NOT NULL, 
  `title` varchar(128) NOT NULL,
  `content` varchar(30) NOT NULL,
  `createdate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`teamnoticeno`),
  FOREIGN KEY(`teamboardno`) REFERENCES teamboard(`teamboardno`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;