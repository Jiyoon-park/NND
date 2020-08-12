
CREATE TABLE `member` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `name` char(8) NOT NULL,
  `email` varchar(128) NOT NULL,
  `profile` varchar(128) default NULL,
  `password` varchar(300) default null,
  `company` varchar(10) default NULL,
  `gitaddr` varchar(128) Default null,
  `memberstack` varchar(500) default null,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `teamboard` (
  `teamboardno` int(10) NOT NULL AUTO_INCREMENT,
  `idx` int default NULL,  
  `email` varchar(128) NOT NULL,
  `teamname` varchar(128) NOT NULL,
  `memberemails` varchar(200) default null,
  `groupsize` int(10) NOT NULL,
  `deadline` varchar(30) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(500) NOT NULL,
  `techstack` varchar(300) DEFAULT NULL,
  `category` varchar(300) NOT NULL,
  `contentstack` varchar(300) DEFAULT NULL,
  `kakaolink` varchar(128) DEFAULT NULL,
  `likecnt` int (10) DEFAULT 0,
  `createdate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`teamboardno`),
  FOREIGN KEY(`idx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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

 CREATE TABLE `memberboard` (  
  `boardno` int(10) NOT NULL AUTO_INCREMENT,
  `idx` int DEFAULT NULL, 
  `email` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `title` varchar(128) NOT NULL,
  `content` varchar(255) NOT NULL,
  `contentstack` varchar(128) NOT NULL,
  `techstack` varchar(128) NOT NULL, 
  `createdate` datetime DEFAULT current_timestamp(),
  `likecnt` int(10) NOT NULL,
  PRIMARY KEY (`boardno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `letter` (
  `letterno` int(10) NOT NULL AUTO_INCREMENT,
  `sendidx` int NOT NULL,
  `receiveidx` int NOT NULL,
  `content` varchar(500) DEFAULT NULL,
  `createdate` datetime DEFAULT current_timestamp(),
  `read` tinyint(1) default 0,
  `lettertype` varchar(50) not null,
  `teamboardno` int not null,
  PRIMARY KEY (`letterno`),
  FOREIGN KEY (`sendidx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (`receiveidx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `projecthistory`(
  `historyno` int not null auto_increment,
  `idx` int NOT NULL default 0,
  `projectname` varchar(128) default null,
  `summary` varchar(100) default null,
  `content` varchar(500) default null,
  `gitlink` varchar(128) default null,
  `usedstack` varchar(200) default null,
  PRIMARY KEY (`historyno`),
  FOREIGN KEY (`idx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE liketeam(
    likeno                      int    NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    tboard                     int    NOT NULL,    
    mno                        int    NOT NULL,
    createdate                datetime DEFAULT current_timestamp(),
    FOREIGN KEY (mno) REFERENCES member (idx) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (tboard) REFERENCES teamboard (teamboardno) ON UPDATE CASCADE ON DELETE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE likemember(
    likeno                      int    NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    mboard                     int    NOT NULL,    
    mno                        int    NOT NULL,
    createdate                datetime DEFAULT current_timestamp(),
    FOREIGN KEY (mno) REFERENCES member (idx) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (mboard) REFERENCES memberboard (boardno) ON UPDATE CASCADE ON DELETE CASCADE
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


 CREATE TABLE `teampost` (  
  `teampostno` int(10) NOT NULL AUTO_INCREMENT,
  `teamboardno` int NOT NULL, 
  `memberidx` int not null,
  `writer` varchar(30) NOT NULL,
  `title` varchar(128) NOT NULL,
  `content` varchar(500) NOT NULL,
  `createdate` varchar(128) NOT NULL,
  `notice` tinyint(1) not null,
  PRIMARY KEY (`teampostno`),
  FOREIGN KEY(`teamboardno`) REFERENCES teamboard(`teamboardno`) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY(`memberidx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE `memberrating` (  
  `ratingno` int(10) NOT NULL AUTO_INCREMENT,
  `idx` int NOT NULL, 
  `commitcnt` int Default 0,
  `issuecnt` int default 0,
  `attendrate` int default 0,
  `satisfaction` int default 0,
  `teamworkship` int default 0,
  `createdate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ratingno`),
  FOREIGN KEY(`idx`) REFERENCES member(`idx`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;