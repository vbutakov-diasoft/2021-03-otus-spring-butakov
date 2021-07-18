package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookCommentNotFoundException;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;

public interface BookService {
    void insert();
    void update() throws BookCommentNotFoundException;
    void delete() throws BookCommentNotFoundException;
    void findAll();

    Book findById(Long id) ;
    Long insertByParam(String title,String authorName, String genreName);
    void updateById(Long id,String title,String authorName, String genreName) ;
    void deleteById(Long id);
    public List<Book> getAll();

    String bookAuthorNameInput();
    String bookGenreNameInput();
    String bookTitleInput();
}
