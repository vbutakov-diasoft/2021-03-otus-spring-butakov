package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

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
    void bookUpdate();
    void bookDelete();
    void bookFindAll();

}
