package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.exception.BookCommentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookCommentDao extends JpaRepository<BookComment, Long> {

    @Transactional(readOnly = true)
    @Query("select c from BookComment c join fetch c.book b where b.bookID = :bookID")
    List<BookComment> findByBookId(@Param("bookID") Long bookID);
}
