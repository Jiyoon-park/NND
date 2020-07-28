CREATE TABLE likemember(
    likeno                      int    NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    mboard                     int    NOT NULL,    
    mno                        int    NOT NULL,
    createdate                datetime DEFAULT current_timestamp(),
    FOREIGN KEY (mno) REFERENCES member (idx),
    FOREIGN KEY (mboard) REFERENCES teamboard (teamboardno)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;