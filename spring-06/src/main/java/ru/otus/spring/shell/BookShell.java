package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;

    @ShellMethod(value = "Book insert", key = {"Book insert", "bi"})
    public void bookInsert() {
        bookService.insert();
    }

    @ShellMethod(value = "Book update", key = {"Book update", "bu"})
    public void bookUpdate() {
        bookService.update();
    }

    @ShellMethod(value = "Book delete", key = {"Book delete", "bd"})
    public void bookDelete() {
        bookService.delete();
    }

    @ShellMethod(value = "Book find all", key = {"Book find all", "bf"})
    public void bookFindAll() {
        bookService.findAll();
    }
}
