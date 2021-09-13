package ru.otus.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Book;

@AllArgsConstructor
@Data
public class BookDto {
    private Long id;
    private String author;
    private String genre;
    private String title;

    public static BookDto bookToDto(Book book) {
        return new BookDto(book.getBookID(), book.getAuthor().getName(), book.getGenre().getName(), book.getTitle());
    }
}
