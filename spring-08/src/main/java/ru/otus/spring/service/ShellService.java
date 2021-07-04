package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.BookCommentNotFoundException;

import java.util.List;

public interface ShellService {

    // Shell service for Author

    void authorInsert();
    void authorUpdate();
    void authorDelete();
    void authorFindAll();

    // Shell service for Genre

    void genreInsert();
    void genreUpdate();
    void genreDelete();
    void genreFindAll();

    // Shell service for Book

    void bookInsert();
    void bookUpdate() throws BookCommentNotFoundException;
    void bookDelete() throws BookCommentNotFoundException;
    void bookFindAll();

    // Shell service for BookComment
    void bookCommentInsert();
    void bookCommentUpdate() throws BookCommentNotFoundException;
    void bookCommentDelete() throws BookCommentNotFoundException;
    void bookCommentFindByBook();
}
