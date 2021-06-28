package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exception.BookCommentNotFoundException;

@Service
public class ShellServiceImpl implements ShellService{

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final BookCommentService bookCommentService;

    public ShellServiceImpl(AuthorService authorService, GenreService genreService, BookService bookService, BookCommentService bookCommentService) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
        this.bookCommentService = bookCommentService;
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
    public void bookUpdate() throws BookCommentNotFoundException {
        bookService.update();
    }

    @Override
    public void bookDelete() throws BookCommentNotFoundException {
        bookService.delete();
    }

    @Override
    public void bookFindAll() {
        bookService.findAll();
    }

    @Override
    public void bookCommentInsert() {
        bookCommentService.insert();
    }

    @Override
    public void bookCommentUpdate() throws BookCommentNotFoundException {
        bookCommentService.update();
    }

    @Override
    public void bookCommentDelete() throws BookCommentNotFoundException {
        bookCommentService.delete();
    }

    @Override
    public void bookCommentFindByBook() {
        bookCommentService.findByBook();
    }

}
