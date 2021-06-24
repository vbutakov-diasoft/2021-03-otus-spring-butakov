package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.GenreService;
import ru.otus.spring.service.ShellService;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final ShellService shellService;

    @ShellMethod(value = "Genre insert", key = {"Genre insert", "gi"})
    public void genreInsert() {
        shellService.genreInsert();
    }

    @ShellMethod(value = "Genre update", key = {"Genre update", "gu"})
    public void genreUpdate() {
        shellService.genreUpdate();
    }

    @ShellMethod(value = "Genre delete", key = {"Genre delete", "gd"})
    public void genreDelete() {
        shellService.genreDelete();
    }

    @ShellMethod(value = "Genre find all", key = {"Genre find all", "gf"})
    public void genreFindAll() {
        shellService.genreFindAll();
    }
}
