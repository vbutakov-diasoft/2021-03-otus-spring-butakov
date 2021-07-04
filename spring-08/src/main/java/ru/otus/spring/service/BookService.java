package ru.otus.spring.service;

import ru.otus.spring.exception.BookCommentNotFoundException;

public interface BookService {
    void insert();
    void update() throws BookCommentNotFoundException;
    void delete() throws BookCommentNotFoundException;
    void findAll();

    String bookAuthorNameInput();
    String bookGenreNameInput();
    String bookTitleInput();
}
