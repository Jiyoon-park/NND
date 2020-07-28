CREATE TABLE liketeam(
    likeno                      int    NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    tboard                     int    NOT NULL,    
    mno                        int    NOT NULL,
    createdate                datetime DEFAULT current_timestamp(),
    FOREIGN KEY (mno) REFERENCES member (idx),
    FOREIGN KEY (tboard) REFERENCES teamboard (teamboardno)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;
