package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.exception.BookCommentNotFoundException;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookCommentDaoJpa implements BookCommentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookComment insert(BookComment bookComment) {
        return entityManager.merge(bookComment);
    }

    @Override
    public void update(BookComment bookComment) throws BookCommentNotFoundException {
        if (!checkExistsByID(bookComment)) {
            throw new BookCommentNotFoundException("Комментарий c ID=" + bookComment.getBookCommentId() + " не найден в базе!");
        }
        else {
            entityManager.merge(bookComment);
        }
    }

    @Override
    public void delete(BookComment bookComment) throws BookCommentNotFoundException {
        if (!checkExistsByID(bookComment)) {
            throw new BookCommentNotFoundException("Комментарий c ID=" + bookComment.getBookCommentId() + " не найден в базе!");
        }
        else {
            entityManager.remove(bookComment);
        }
    }

    @Override
    public List<BookComment> findByBookId(Long BookID) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("BookCommentWithBook");
        TypedQuery<BookComment> query = entityManager.createQuery(
                "select c from BookComment c join fetch c.book b where b.bookID = :bookID", BookComment.class);
        query.setParameter("bookID", BookID);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<BookComment> findByID(Long BookCommentId) {
        return Optional.of(entityManager.find(BookComment.class, BookCommentId));
    }

    @Override
    public boolean checkExistsByID(BookComment bookComment) {
        long res = 0L;
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(a) from BookComment a where a.BookCommentID = :bookCommentID",
                Long.class);
        query.setParameter("bookCommentID", bookComment.getBookCommentId());
        res = query.getSingleResult();
        return res > 0;
    }
}
