package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreAlreadyExistsException;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.List;
import java.util.Optional;

public interface GenreDao extends JpaRepository<Genre, Long> {

    List<Genre> findByName(String name);
}
