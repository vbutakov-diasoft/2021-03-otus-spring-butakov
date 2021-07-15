package ru.otus.spring.service;

import ru.otus.spring.domain.BookComment;
import ru.otus.spring.exception.BookCommentNotFoundException;

import java.util.List;

public interface BookCommentService {

    void insert();
    void update() throws BookCommentNotFoundException;
    void delete() throws BookCommentNotFoundException;
    void findByBook();

    Long bookIDInput();
    Long bookCommentIDInput();
    String bookCommentCommentInput();
    void bookCommentListOutput(List<BookComment> list);
}
