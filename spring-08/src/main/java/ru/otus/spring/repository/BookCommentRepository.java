package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {

    @Query("select c from BookComment c join fetch c.book b where b.bookID = :bookID")
    List<BookComment> findByBookId(@Param("bookID") Long bookID);
}
