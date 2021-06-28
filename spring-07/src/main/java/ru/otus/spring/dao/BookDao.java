package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookAlreadyExistsException;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    void insert(Book book) throws BookAlreadyExistsException;
    void update(Book book) throws BookNotFoundException;
    void delete(Book book) throws BookNotFoundException;

    List<Book> findAll();
    Optional<Book> findByID(Long bookID);

    boolean checkExistsByParam(Book book);
    boolean checkExistsByID(Book book);
}
