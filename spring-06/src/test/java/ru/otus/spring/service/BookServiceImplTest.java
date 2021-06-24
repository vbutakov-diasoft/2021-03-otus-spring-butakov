package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.BookAlreadyExistsException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис для работы с книгами ")
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceImplTest {

    private static final Long DEFAULT_BOOK_ID = 1L;
    private static final Long DEFAULT_GENRE_ID = 1L;
    private static final Long DEFAULT_AUTHOR_ID = 1L;

    private static final String DEFAULT_BOOK_NAME = "Tom Sawyer";
    private static final String DEFAULT_AUTHOR_NAME = "Mark Twain";
    private static final String DEFAULT_GENRE_NAME = "Novel";

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private MessageSource messageMock;

    @MockBean
    private ShellServiceImpl shellService;

    @MockBean
    private MessageServiceImpl messageService;

    @Test
    @DisplayName("должен корректно добавлять в базу книгу")
    void shouldCorrectInsertBook() throws BookAlreadyExistsException{

        Mockito.when(bookService.bookAuthorNameInput()).thenReturn(DEFAULT_AUTHOR_NAME);
        Author author = new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME);
        Mockito.when(authorDao.findByName(anyString())).thenReturn(Collections.singletonList(author));

        Genre genre = new Genre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME);
        Mockito.when(genreDao.findByName(any())).thenReturn(Collections.singletonList(genre));

        Mockito.when(bookService.bookTitleInput()).thenReturn(DEFAULT_BOOK_NAME);
        Book book = new Book(DEFAULT_BOOK_ID, DEFAULT_BOOK_NAME, author, genre);

        shellService.bookInsert();

        ArgumentCaptor<Book> argument = ArgumentCaptor.forClass(Book.class);

        verify(bookDao, Mockito.times(1)).insert(argument.capture());
        assertEquals(DEFAULT_BOOK_NAME, argument.getValue().getTitle());
        assertEquals(DEFAULT_AUTHOR_ID, argument.getValue().getAuthor().getAuthorID());
        assertEquals(DEFAULT_GENRE_ID, argument.getValue().getGenre().getGenreID());
    }
}
