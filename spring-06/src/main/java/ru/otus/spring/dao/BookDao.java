package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookAlreadyExistsException;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book insert(Book book) throws BookAlreadyExistsException;
    void update(Book book) throws BookNotFoundException;
    void delete(Book book) throws BookNotFoundException;

    List<Book> findAll();
    Optional<Book> findByID(Long bookID);
    List<Book> findBookByParam(Long authorID, Long genreID, String title);

    boolean checkExists(Book book);
}
