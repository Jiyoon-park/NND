CREATE TABLE `teamregist` (
  `teamregistno` int(10) NOT NULL AUTO_INCREMENT,
  `teamboardno` int Not NULL,  
  `memberidx` int not null,
  `memberemail` varchar(100) not null,
  `createdate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`teamregistno`),
  FOREIGN KEY(`teamboardno`) REFERENCES teamboard(`teamboardno`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(`memberidx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;