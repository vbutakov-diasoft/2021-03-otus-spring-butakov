package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.exception.BookCommentNotFoundException;
import ru.otus.spring.service.ShellService;

@ShellComponent
@RequiredArgsConstructor
public class BookCommentShell {

    private final ShellService shellService;

    @ShellMethod(value = "Comment insert", key = {"Comment insert", "bci"})
    public void commentInsert() {
        shellService.bookCommentInsert();
    }

    @ShellMethod(value = "Comment update", key = {"Comment update", "bcu"})
    public void commentUpdate() throws BookCommentNotFoundException {
        shellService.bookCommentUpdate();
    }

    @ShellMethod(value = "Comment delete", key = {"Comment delete", "bcd"})
    public void commentDelete() throws BookCommentNotFoundException {
        shellService.bookCommentDelete();
    }

    @ShellMethod(value = "Comment find all for book", key = {"Comment find all for book", "bcb"})
    public void commentFindAllForBook() {
        shellService.bookCommentFindByBook();
    }
}
