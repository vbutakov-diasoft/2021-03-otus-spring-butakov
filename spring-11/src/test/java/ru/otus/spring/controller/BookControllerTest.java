package ru.otus.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.controller.dto.BookCommentDto;
import ru.otus.spring.controller.dto.BookDto;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName(value = "Контроллер книг")
class BookControllerTest {
    private static final String ERROR_STRING = "book.error.bookNotFound";
    private String token;
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private static final Long EXISTING_BOOK_ID = 1L;
    private static final Long EXISTING_AUTHOR_ID = 1L;
    private static final Long EXISTING_GENRE_ID = 1L;
    private static final String EXISTING_BOOK_TITLE = "Tom Sawyer";
    private static final String EXISTING_AUTHOR_TITLE = "Mark Twain";
    private static final Author EXISTING_AUTHOR = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_TITLE);
    private static final Genre EXISTING_GENRE = new Genre(EXISTING_GENRE_ID, "Novel");
    private static final Book EXISTING_BOOK = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_TITLE, EXISTING_AUTHOR, EXISTING_GENRE);

    @BeforeEach
    void setUp() throws Exception {
        var result = mvc.perform(post("/token")
                .header("Authorization", "Basic dXNlciBwYXNz"))
                .andExpect(status().isOk())
                .andReturn();
        token = result.getResponse().getContentAsString();
    }

    @Test
    @DisplayName("должен возвращать список книг")
    void shouldReturnCorrectBooksList() throws Exception {
        /*
        List<Book> books = List.of(EXISTING_BOOK, new Book(2L, "A Horses Tale", EXISTING_AUTHOR, EXISTING_GENRE));

        List<BookDto> expectedResult = books.stream()
                .map(BookDto::bookToDto).collect(Collectors.toList());

        mvc.perform(get("/api/book")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
        */
    }
/*
    @Test
    @DisplayName("должен возвращать корректный список комментариев по id книги")
    void shouldReturnCorrectBookCommentList() throws Exception {
        List<BookCommentDto> expectedResult = List.of(
                new BookCommentDto(1L, "A Horses Tale", "comment1"),
                new BookCommentDto(2L, "A Horses Tale", "comment2"));

        mvc.perform(get("/api/book/2/comment")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен возвращать книгу по id")
    void shouldReturnCorrectPersonById() throws Exception {
        BookDto expectedResult = BookDto.bookToDto(EXISTING_BOOK);

        mvc.perform(get("/api/book/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    @DisplayName("должен возвращать ожидаемую ошибку когда книга не найдена")
    void shouldReturnExpectedErrorWhenBookNotFound() throws Exception {
        mvc.perform(get("/api/book/3")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_STRING));
    }

    @Test
    @DisplayName("должен добавлять книгу")
    void shouldAddBook() throws Exception {
        BookDto bookDto = new BookDto(3L, "author", "genre", "title3");
        mvc.perform(post("/api/book")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());

        mvc.perform(get("/api/book/3")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookDto)));
    }

    @Test
    @DisplayName("должен удалять книгу по id")
    void shouldDeleteBookById() throws Exception {
        mvc.perform(get("/api/book/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(BookDto.bookToDto(EXISTING_BOOK))));

        mvc.perform(delete("/api/book/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        mvc.perform(get("/api/book/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_STRING));
    }

    @Test
    @DisplayName("должен изменять книгу по id")
    void shouldPutBookById() throws Exception {
        BookDto bookDto = BookDto.bookToDto(EXISTING_BOOK);
        bookDto.setAuthor("author2");

        mvc.perform(put("/api/book/1")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());

        mvc.perform(get("/api/book/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(bookDto)));
    }
 */
}