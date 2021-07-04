package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.BookAlreadyExistsException;
import ru.otus.spring.exception.BookNotFoundException;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(Book book) throws BookAlreadyExistsException {
        if (checkExistsByParam(book)) {
            throw new BookAlreadyExistsException(" Книга " + book.getTitle() + " уже добавлена в базу!");
        }
        entityManager.persist(book);
    }

    @Override
    public void update(Book book) throws BookNotFoundException {
        if (!checkExistsByID(book)) {
            throw new BookNotFoundException(" Книга с ID=" + book.getBookID() + " не найдена в базе!");
        }
        entityManager.merge(book);
    }

    @Override
    public void delete(Book book) throws BookNotFoundException {
        if (!checkExistsByID(book)) {
            throw new BookNotFoundException(" Книга с ID=" + book.getBookID() + " не найдена в базе!");
        }
        Query query = entityManager.createQuery("delete from Book b where b.bookID = :bookID");
        query.setParameter("bookID", book.getBookID());
        query.executeUpdate();
    }

    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("BookWithAuthorAndGenre");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findByID(Long bookID) {
        TypedQuery<Book> query = entityManager.createQuery(
                "select b from Book b where b.bookID = :bookID",
                Book.class);
        query.setParameter("bookID", bookID);

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public boolean checkExistsByParam(Book book) {
        Long res = 0L;
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(b) from Book b where b.title = :title and authorID = :authorID and genreID = :genreID",
                Long.class);
        query.setParameter("title", book.getTitle());
        query.setParameter("authorID", book.getAuthor().getAuthorID());
        query.setParameter("genreID", book.getGenre().getGenreID());
        res = query.getSingleResult();
        return res > 0;
    }

    @Override
    public boolean checkExistsByID(Book book) {
        Long res = 0L;
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(b) from Book b where b.bookID = :bookID",
                Long.class);
        query.setParameter("bookID", book.getBookID());
        res = query.getSingleResult();
        return res > 0;
    }
}