package ru.otus.spring.dao;
/*
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.exception.*;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DAO для работы с книгами ")
@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {

    private static final Long DEFAULT_BOOK_ID = 1L;
    private static final String DEFAULT_BOOK_NAME = "Tom Sawyer";

    private static final Long NEW_BOOK_ID = 2L;
    private static final String NEW_BOOK_NAME = "Huckleberry Finn";

    private static final Long DEFAULT_AUTHOR_ID = 1L;
    private static final String DEFAULT_AUTHOR_NAME = "Mark Twain";

    private static final Long DEFAULT_GENRE_ID = 1L;
    private static final String DEFAULT_GENRE_NAME = "Novel";

    @Autowired
    private BookDao bookDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("должен корректно добавлять в базу книгу")
    void shouldCorrectInsertBook() throws BookAlreadyExistsException {
        Book newBook = new Book(0L, NEW_BOOK_NAME,
                new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME),
                new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME));

        bookDao.insert(newBook);

        Optional<Book> bookDB  = bookDao.findByID(NEW_BOOK_ID);
        System.out.println(bookDB.get().getBookID());
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("bookID", NEW_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("title", NEW_BOOK_NAME);
    }

    @Test
    @DisplayName("должен корректно удалять книгу из базы")
    void shouldCorrectDeleteBook() throws BookNotFoundException{
        Book book  = new Book(DEFAULT_BOOK_ID, DEFAULT_BOOK_NAME,
                new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME),
                new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME));

        bookDao.delete(book);

        assertThat(bookDao.checkExistsByID(book)).isFalse();
    }

    @Test
    @DisplayName("должен правильно находить книгу по идентификатору")
    void shouldFindBookByID() {
        Optional<Book> bookDB = bookDao.findByID(DEFAULT_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("bookID", DEFAULT_BOOK_ID);
        assertThat(bookDB).isNotEmpty().get().hasFieldOrPropertyWithValue("title", DEFAULT_BOOK_NAME);
    }
}
*/