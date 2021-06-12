package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.exception.*;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с книгами ")
@ExtendWith(SpringExtension.class)
@JdbcTest
@Import(BookDaoImpl.class)
public class BookDaoImplTest {

    private static final Long DEFAULT_BOOK_ID = 1L;
    private static final Long NEW_BOOK_ID = 2L;

    private static final String DEFAULT_BOOK_NAME = "Tom Sawyer";
    private static final String DEFAULT_BOOK_NAME2 = "The Adventures of Tom Sawyer";
    private static final String NEW_BOOK_NAME = "Huckleberry Finn";

    private static final Long DEFAULT_GENRE_ID = 1L;
    private static final Long DEFAULT_AUTHOR_ID = 1L;

    @Autowired
    private BookDaoImpl bookDaoImpl;

    @Test
    @DisplayName("должен корректно добавлять в базу книгу")
    void shouldCorrectInsertBook() throws BookAlreadyExistsException {
        Book newBook = new Book(0L, NEW_BOOK_NAME, new Author(DEFAULT_AUTHOR_ID,""), new Genre(DEFAULT_GENRE_ID, ""));
        Book book = bookDaoImpl.insert(newBook);
        assertThat(bookDaoImpl.checkExists(newBook)).isEqualTo(true);

        Book foundBook = bookDaoImpl.findByID(NEW_BOOK_ID).orElse(null);
        Author author = foundBook.getAuthor();
        Genre genre   = foundBook.getGenre();
        assertThat(genre).hasFieldOrPropertyWithValue("genreID", DEFAULT_GENRE_ID);
        assertThat(author).hasFieldOrPropertyWithValue("authorID", DEFAULT_AUTHOR_ID);
    }

    @Test
    @DisplayName("должен корректно изменять книгу в базе")
    void shouldCorrectUpdateBook() throws BookNotFoundException {
        Book updBook = new Book(DEFAULT_BOOK_ID, DEFAULT_BOOK_NAME2, new Author(DEFAULT_AUTHOR_ID,""), new Genre(DEFAULT_GENRE_ID, ""));
        bookDaoImpl.update(updBook);
        Book foundBook = bookDaoImpl.findByID(DEFAULT_BOOK_ID).orElse(null);
        assertThat(foundBook.getTitle()).isEqualTo(DEFAULT_BOOK_NAME2);
    }

    @Test
    @DisplayName("должен корректно удалять книгу из базы")
    void shouldCorrectDeleteBook() throws BookNotFoundException{
        Book delBook = new Book(DEFAULT_BOOK_ID, DEFAULT_BOOK_NAME, null, null);
        bookDaoImpl.delete(delBook);
        assertThat(bookDaoImpl.checkExists(delBook)).isEqualTo(false);
    }

    @Test
    @DisplayName("должен правильно находить книгу по параметрам")
    void shouldFindBookByParam() {
        List<Book> bookList = bookDaoImpl.findBookByParam(DEFAULT_AUTHOR_ID, DEFAULT_GENRE_ID, DEFAULT_BOOK_NAME);
        assertThat(bookList.size()).isEqualTo(1);
        assertThat(bookList.get(0).getBookID()).isEqualTo(DEFAULT_BOOK_ID);
    }

    @Test
    @DisplayName("должен правильно находить книгу по идентификатору")
    void shouldFindBookByID() {
        Book book = bookDaoImpl.findByID(DEFAULT_BOOK_ID).orElse(null);
        assertThat(book.getTitle()).isEqualTo(DEFAULT_BOOK_NAME);
    }
}
