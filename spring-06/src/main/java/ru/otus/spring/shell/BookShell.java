package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.ShellService;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final ShellService shellService;

    @ShellMethod(value = "Book insert", key = {"Book insert", "bi"})
    public void bookInsert() {
        shellService.bookInsert();
    }

    @ShellMethod(value = "Book update", key = {"Book update", "bu"})
    public void bookUpdate() {
        shellService.bookUpdate();
    }

    @ShellMethod(value = "Book delete", key = {"Book delete", "bd"})
    public void bookDelete() {
        shellService.bookDelete();
    }

    @ShellMethod(value = "Book find all", key = {"Book find all", "bf"})
    public void bookFindAll() {
        shellService.bookFindAll();
    }
}
