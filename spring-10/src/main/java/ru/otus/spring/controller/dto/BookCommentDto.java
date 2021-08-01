package ru.otus.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.BookComment;

@AllArgsConstructor
@Data
public class BookCommentDto {
    private Long id;
    private String bookTitle;
    private String comment;

    public static BookCommentDto bookCommentToDto(BookComment bookComment) {
        return new BookCommentDto(bookComment.getBookCommentId(), bookComment.getBook().getTitle(), bookComment.getComment());
    }
}
