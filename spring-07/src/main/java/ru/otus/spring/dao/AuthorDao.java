package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorAlreadyExistsException;
import ru.otus.spring.exception.AuthorNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Author insert(Author author) throws AuthorAlreadyExistsException;
    Author update(Author author) throws AuthorNotFoundException;
    void delete(Author author) throws AuthorNotFoundException;

    List<Author> findAll();
    Optional<Author> findByID(Long authorID);
    List<Author> findByName(String name);

    boolean checkExistsByName(Author author);
    boolean checkExistsByID(Author author);
}
