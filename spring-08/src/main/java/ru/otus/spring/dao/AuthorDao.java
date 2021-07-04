package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorAlreadyExistsException;
import ru.otus.spring.exception.AuthorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AuthorDao extends JpaRepository<Author, Long> {

    List<Author> findByName(String name);
}
