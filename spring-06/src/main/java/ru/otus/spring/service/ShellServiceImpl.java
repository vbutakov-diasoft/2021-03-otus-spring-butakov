package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;

import java.util.List;

@Service
public class ShellServiceImpl implements ShellService{

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    public ShellServiceImpl(AuthorService authorService, GenreService genreService, BookService bookService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
    }

    public void authorInsert() {
        authorService.insert();
    }

    public void authorUpdate() {
        authorService.update();
    }

    public void authorDelete() {
        authorService.delete();
    }

    public void authorFindAll(){
        authorService.findAll();
    }

    @Override
    public void genreInsert() {
        genreService.insert();
    }

    @Override
    public void genreUpdate() {
        genreService.update();
    }

    @Override
    public void genreDelete() {
        genreService.delete();
    }

    @Override
    public void genreFindAll() {
        genreService.findAll();
    }

    @Override
    public void bookInsert() {
        bookService.insert();
    }

    @Override
    public void bookUpdate() {
        bookService.update();
    }

    @Override
    public void bookDelete() {
        bookService.delete();
    }

    @Override
    public void bookFindAll() {
        bookService.findAll();
    }

}
