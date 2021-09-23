INSERT INTO AUTHOR(AUTHORID, NAME) values (1, 'Mark Twain');
INSERT INTO GENRE(GENREID, NAME) values (1, 'Novel');
INSERT INTO BOOK(BOOKID, title, AUTHORID, GENREID) values(1, 'Tom Sawyer', 1, 1);
INSERT INTO BOOK(BOOKID, title, AUTHORID, GENREID) values(2, 'A Horses Tale', 1, 1);
insert into BOOK_COMMENT(BOOK_COMMENT_ID, BOOKID, comment) values(1, 2, 'comment1');
insert into BOOK_COMMENT(BOOK_COMMENT_ID, BOOKID, comment) values(2, 2, 'comment2');
insert into BOOK_COMMENT(BOOK_COMMENT_ID, BOOKID, comment) values(3, 1, 'comment3');
insert into USER(USERID, LOGIN, PASSWORD) values(1, 'user', 'pass');