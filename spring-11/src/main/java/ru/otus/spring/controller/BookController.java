package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.controller.dto.BookCommentDto;
import ru.otus.spring.controller.dto.BookDto;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.service.BookCommentService;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final BookCommentService bookCommentService;

    @GetMapping("/api/book")
    public List<BookDto> getAll() {
        return bookService.getAll().stream()
                .map(BookDto::bookToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/book/{id}")
    public BookDto getById(@PathVariable("id") long id) {
        return BookDto.bookToDto(bookService.findById(id));
    }

    @GetMapping("/api/book/{id}/comment")
    public List<BookCommentDto> getAllBookComments(@PathVariable("id") long id) {
        return bookCommentService.getAllCommentsByBookID(id).stream()
                .map(BookCommentDto::bookCommentToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/book")
    @Secured( "ROLE_ADMIN" )
    public BookDto postBook(@RequestBody BookDto book) {
        book.setId(bookService.insertByParam(book.getTitle(), book.getAuthor(), book.getGenre()));
        return book;
    }

    @PutMapping("/api/book/{id}")
    public BookDto putBook(@PathVariable("id") long id, @RequestBody BookDto book) {
        bookService.updateById(id, book.getTitle(), book.getAuthor(), book.getGenre());
        return book;
    }

    @DeleteMapping("/api/book/{id}")
    public void deleteBookById(@PathVariable("id") long id) {
        bookService.deleteById(id);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFound(BookNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
