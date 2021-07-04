package ru.otus.spring.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.BookAlreadyExistsException;
import ru.otus.spring.exception.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph(value = "BookWithAuthorAndGenre")
    List<Book> findAll();
}
