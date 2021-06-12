package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService genreService;

    @ShellMethod(value = "Genre insert", key = {"Genre insert", "gi"})
    public void genreInsert() {
        genreService.insert();
    }

    @ShellMethod(value = "Genre update", key = {"Genre update", "gu"})
    public void genreUpdate() {
        genreService.update();
    }

    @ShellMethod(value = "Genre delete", key = {"Genre delete", "gd"})
    public void genreDelete() {
        genreService.delete();
    }

    @ShellMethod(value = "Genre find all", key = {"Genre find all", "gf"})
    public void genreFindAll() {
        genreService.findAll();
    }
}
