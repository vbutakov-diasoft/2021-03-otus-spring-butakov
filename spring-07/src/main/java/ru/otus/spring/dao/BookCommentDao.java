package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.exception.BookCommentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookCommentDao {

    BookComment insert(BookComment bookComment);
    void update(BookComment bookComment) throws BookCommentNotFoundException;
    void delete(BookComment bookComment) throws BookCommentNotFoundException;

    List<BookComment> findByBookId(Long BookID);
    Optional<BookComment> findByID(Long BookCommentId);

    boolean checkExistsByID(BookComment bookComment);
}
