package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final MessageService messageService;
    private final InputOutputService inputOutputService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, MessageService messageService, InputOutputService inputOutputService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.inputOutputService = inputOutputService;
        this.messageService = messageService;
    }

    private Optional<Author> getAuthor() {
        Author author = null;
        String authorName = bookAuthorNameInput();
        List<Author> authors = authorRepository.findByName(authorName);
        if (authors.size() > 1) {
            messageService.messagePrintOut("book.error.foundToManyAuthors");
        } else if (authors.size() == 1) {
            author = authors.get(0);
        }
        if (authors.size() == 0) {
            author = authorRepository.save(new Author(0L, authorName));

        }
        return Optional.ofNullable(author);
    }

    private Optional<Genre> getGenre() {
        Genre genre = null;
        String genreName = bookGenreNameInput();
        List<Genre> genres = genreRepository.findByName(genreName);
        if (genres.size() > 1) {
            messageService.messagePrintOut("book.error.foundToManyGenres");
        } else if (genres.size() == 1) {
            genre = genres.get(0);
        }
        if (genres.size() == 0) {
            genre = genreRepository.save(new Genre(0L, genreName));
        }
        return Optional.ofNullable(genre);
    }

    @Transactional
    @Override
    public void insert() {
        Author author = getAuthor().orElse(null);
        if (author == null)
            return;

        Genre genre = getGenre().orElse(null);
        if (genre == null)
            return;

        String title = bookTitleInput();
        Book book = new Book(0L, title, author, genre);
        try {
            bookRepository.save(book);
            messageService.messagePrintOut("book.success.insert", new Object[]{book.getBookID(), book.getTitle()});
        } catch (Exception e) {
            messageService.messagePrintOut("book.error.insert", e.getMessage());
        }
    }

    @Transactional
    @Override
    public void update() {
        messageService.messagePrintOut("book.ID.input");
        Long bookID = inputOutputService.readLong();
        if (!bookRepository.existsById(bookID)) {
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        Book book = bookRepository.findById(bookID).orElse(null);
        String message = messageService.getMessage("book.ID.output")
                + String.valueOf(book.getBookID()) + "; "
                + messageService.getMessage("book.title.output")
                + book.getTitle() + "; "
                + messageService.getMessage("book.authorName.output")
                + book.getAuthor().getName() + "; "
                + messageService.getMessage("book.genreName.output")
                + book.getGenre().getName() + "; ";
        inputOutputService.printOut(message);

        Author author = getAuthor().orElse(null);
        if (author == null)
            return;

        Genre genre = getGenre().orElse(null);
        if (genre == null)
            return;

        String title = bookTitleInput();
        Book newBook = new Book(bookID, title, author, genre);
        try {
            bookRepository.save(newBook);
            messageService.messagePrintOut("book.success.update", new Object[]{newBook.getBookID(), newBook.getTitle()});
        } catch (Exception e) {
            messageService.messagePrintOut("book.error.update", e.getMessage());
        }
    }

    @Transactional
    @Override
    public void delete() {
        messageService.messagePrintOut("book.ID.input");
        Long bookID = inputOutputService.readLong();
        if (!bookRepository.existsById(bookID)) {
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }

        try {
            bookRepository.delete(new Book(bookID, "", null, null));
            messageService.messagePrintOut("book.success.delete", new Object[]{bookID});
        } catch (Exception e) {
            messageService.messagePrintOut("book.error.delete", e.getMessage());
        }

    }

    @Transactional(readOnly = true)
    @Override
    public void findAll() {
        List<Book> list = bookRepository.findAll();
        if (list.size() == 0) {
            messageService.messagePrintOut("book.list.empty");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Book book = list.get(i);
            String message = messageService.getMessage("book.ID.output")
                    + String.valueOf(book.getBookID()) + "; "
                    + messageService.getMessage("book.title.output")
                    + book.getTitle() + "; "
                    + messageService.getMessage("book.authorName.output")
                    + book.getAuthor().getName() + "; "
                    + messageService.getMessage("book.genreName.output")
                    + book.getGenre().getName() + "; ";
            inputOutputService.printOut(message);
        }
    }

    @Override
    public String bookAuthorNameInput() {
        messageService.messagePrintOut("book.authorName.input");
        return inputOutputService.readString();
    }

    @Override
    public String bookGenreNameInput() {
        messageService.messagePrintOut("book.genreName.input");
        return inputOutputService.readString();
    }

    @Override
    public String bookTitleInput() {
        messageService.messagePrintOut("book.title.input");
        return inputOutputService.readString();
    }
}
