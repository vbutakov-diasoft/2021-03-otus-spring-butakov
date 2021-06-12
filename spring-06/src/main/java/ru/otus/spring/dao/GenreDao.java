package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreAlreadyExistsException;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    Genre insert(Genre genre) throws GenreAlreadyExistsException;
    void update(Genre genre) throws GenreNotFoundException;
    void delete(Genre genre) throws GenreNotFoundException;

    List<Genre> findAll();
    Optional<Genre> findByID(Long genreID);
    List<Genre> findByName(String name);
    boolean checkExists(Genre genre);
}
