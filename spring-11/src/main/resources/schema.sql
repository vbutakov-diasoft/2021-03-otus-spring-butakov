DROP TABLE IF EXISTS AUTHOR CASCADE;
CREATE TABLE AUTHOR(AUTHORID LONG AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255));

DROP TABLE IF EXISTS GENRE CASCADE;
CREATE TABLE GENRE(GENREID LONG AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(255));

DROP TABLE IF EXISTS BOOK CASCADE;
CREATE TABLE BOOK(BOOKID LONG AUTO_INCREMENT PRIMARY KEY, AUTHORID LONG, GENREID LONG, TITLE VARCHAR(255),
                  FOREIGN KEY(AUTHORID) REFERENCES AUTHOR(AUTHORID) ON DELETE CASCADE,
                  FOREIGN KEY(GENREID) REFERENCES GENRE(GENREID) ON DELETE CASCADE
                  );

DROP TABLE IF EXISTS BOOK_COMMENT CASCADE;
CREATE TABLE BOOK_COMMENT(BOOK_COMMENT_ID LONG AUTO_INCREMENT PRIMARY KEY, BOOKID LONG, COMMENT VARCHAR(255),
                          FOREIGN KEY(BOOKID) REFERENCES BOOK(BOOKID) ON DELETE CASCADE
                          );

DROP TABLE IF EXISTS USER;
CREATE TABLE USER(USERID LONG AUTO_INCREMENT PRIMARY KEY, LOGIN VARCHAR(255), PASSWORD VARCHAR(255));