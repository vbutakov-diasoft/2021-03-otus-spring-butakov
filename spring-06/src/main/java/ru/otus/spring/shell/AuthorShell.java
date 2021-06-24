package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.AuthorService;
import ru.otus.spring.service.ShellService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final ShellService shellService;

    @ShellMethod(value = "Author insert", key = {"Author insert", "ai"})
    public void authorInsert() {
        shellService.authorInsert();
    }

    @ShellMethod(value = "Author update", key = {"Author update", "au"})
    public void authorUpdate() {
        shellService.authorUpdate();
    }

    @ShellMethod(value = "Author delete", key = {"Author delete", "ad"})
    public void authorDelete() {
        shellService.authorDelete();
    }

    @ShellMethod(value = "Author find all", key = {"Author find all", "af"})
    public void authorFindAll() {
        shellService.authorFindAll();
    }
}
