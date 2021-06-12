package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.AuthorAlreadyExistsException;
import ru.otus.spring.exception.BookAlreadyExistsException;
import ru.otus.spring.exception.BookNotFoundException;
import ru.otus.spring.exception.GenreAlreadyExistsException;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final ShellService shellService;
    private final MessageService messageService;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, ShellService shellService, MessageService messageService) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.shellService = shellService;
        this.messageService = messageService;
    }

    private Optional<Author> getAuthor(){
        Author author = null;
        String authorName = shellService.bookAuthorNameInput();
        List<Author> authors = authorDao.findByName(authorName);
        if (authors.size() > 1) {
            messageService.messagePrintOut("book.error.foundToManyAuthors");
        } else
        if (authors.size() == 1) {
            author = authors.get(0);
        }
        if (authors.size() == 0){
            try {
                author = authorDao.insert(new Author(0L, authorName));
            } catch (AuthorAlreadyExistsException e) {
                messageService.messagePrintOut("author.error.insert", e.getMessage());
            }
        }
        return Optional.ofNullable(author);
    }

    private Optional<Genre> getGenre(){
        Genre genre = null;
        String genreName = shellService.bookGenreNameInput();
        List<Genre> genres = genreDao.findByName(genreName);
        if (genres.size() > 1) {
            messageService.messagePrintOut("book.error.foundToManyGenres");
        } else
        if (genres.size() == 1) {
            genre = genres.get(0);
        }
        if (genres.size() == 0){
            try {
                genre = genreDao.insert(new Genre(0L, genreName));
            } catch (GenreAlreadyExistsException e) {
                messageService.messagePrintOut("genre.error.insert", e.getMessage());
            }
        }
        return Optional.ofNullable(genre);
    }

    @Override
    public void insert() {
        Author author = getAuthor().orElse(null);
        if (author == null)
            return;

        Genre genre = getGenre().orElse(null);
        if (genre == null)
            return;

        String title = shellService.bookTitleInput();
        Book book = new Book(0L, title, author, genre);
        try {
            bookDao.insert(book);
            messageService.messagePrintOut("book.success.insert", new Object[] {book.getBookID(), book.getTitle()});
        } catch (BookAlreadyExistsException e){
            messageService.messagePrintOut("book.error.insert", e.getMessage());
        }
    }

    @Override
    public void update() {
        Long bookID = shellService.bookIDInput();
        if (!bookDao.checkExists(new Book(bookID, null, null, null))){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        Book book = bookDao.findByID(bookID).orElse(null);
        shellService.bookOutput(book);

        Author author = getAuthor().orElse(null);
        if (author == null)
            return;

        Genre genre = getGenre().orElse(null);
        if (genre == null)
            return;

        String title = shellService.bookTitleInput();
        Book newBook = new Book(bookID, title, author, genre);
        try {
            bookDao.update(newBook);
            messageService.messagePrintOut("book.success.update", new Object[] {newBook.getBookID(), newBook.getTitle()});
        } catch (Exception e){
            messageService.messagePrintOut("book.error.update", e.getMessage());
        }
    }

    @Override
    public void delete() {
        Long bookID = shellService.bookIDInput();
        if (!bookDao.checkExists(new Book(bookID, null, null, null))){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }

        try {
            bookDao.delete(new Book(bookID, "", null, null));
            messageService.messagePrintOut("book.success.delete", new Object[] {bookID});
        } catch (BookNotFoundException e) {
            messageService.messagePrintOut("book.error.delete", e.getMessage());
        }

    }

    @Override
    public void findAll() {
        List<Book> list = bookDao.findAll();
        shellService.bookListOutput(list);
    }
}
