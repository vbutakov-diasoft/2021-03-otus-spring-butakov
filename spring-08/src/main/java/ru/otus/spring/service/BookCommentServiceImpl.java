package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repository.BookCommentRepository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.exception.BookCommentNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class BookCommentServiceImpl implements BookCommentService {

    private final BookRepository bookRepository;
    private final MessageService messageService;
    private final InputOutputService inputOutputService;
    private final BookCommentRepository bookCommentRepository;

    public BookCommentServiceImpl(BookRepository bookRepository, MessageService messageService, InputOutputService inputOutputService, BookCommentRepository bookCommentRepository) {
        this.bookRepository = bookRepository;
        this.messageService = messageService;
        this.inputOutputService = inputOutputService;
        this.bookCommentRepository = bookCommentRepository;
    }

    @Transactional
    @Override
    public void insert() {
        Long bookID = bookIDInput();
        if (!bookRepository.existsById(bookID)){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        Optional<Book> book = bookRepository.findById(bookID);
        String comment = bookCommentCommentInput();
        bookCommentRepository.save(new BookComment(0L, book.get(), comment));
    }

    @Transactional
    @Override
    public void update() throws BookCommentNotFoundException {
        Long bookID = bookIDInput();
        if (!bookRepository.existsById(bookID)){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        Optional<Book> book = bookRepository.findById(bookID);
        List<BookComment> list = bookCommentRepository.findByBookId(bookID);
        bookCommentListOutput(list);
        Long commentID = bookCommentIDInput();
        Optional<BookComment> bookComment = bookCommentRepository.findById(commentID);
        if (!bookComment.isPresent()){
            messageService.messagePrintOut("bookcomment.error.coomentNotFound");
            return;
        }
        String comment = bookCommentCommentInput();
        bookComment.get().setComment(comment);
        bookCommentRepository.save(bookComment.get());
    }

    @Transactional
    @Override
    public void delete() throws BookCommentNotFoundException {
        Long bookID = bookIDInput();
        if (!bookRepository.existsById(bookID)){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        Optional<Book> book = bookRepository.findById(bookID);
        List<BookComment> list = bookCommentRepository.findByBookId(bookID);
        bookCommentListOutput(list);
        Long commentID = bookCommentIDInput();
        Optional<BookComment> comment = bookCommentRepository.findById(commentID);
        if (!comment.isPresent()){
            messageService.messagePrintOut("bookcomment.error.coomentNotFound");
            return;
        }
        bookCommentRepository.delete(comment.get());
    }

    @Transactional(readOnly=true)
    @Override
    public void findByBook() {
        Long bookID = bookIDInput();
        if (!bookRepository.existsById(bookID)){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        Optional<Book> book = bookRepository.findById(bookID);
        List<BookComment> list = bookCommentRepository.findByBookId(bookID);
        bookCommentListOutput(list);
    }

    @Override
    public Long bookIDInput() {
        messageService.messagePrintOut("book.ID.input");
        Long bookID = inputOutputService.readLong();
        return bookID;
    }

    @Override
    public Long bookCommentIDInput() {
        messageService.messagePrintOut("bookcomment.ID.input");
        Long commentID = inputOutputService.readLong();
        inputOutputService.readString();
        return commentID;
    }

    @Override
    public String bookCommentCommentInput() {
        messageService.messagePrintOut("bookcomment.comment.input");
        String comment = inputOutputService.readString();
        return comment;
    }

    @Override
    public void bookCommentListOutput(List<BookComment> list) {
        if (list.size() == 0) {
            messageService.messagePrintOut("bookcomment.list.empty");
            return;
        }

        for(int i = 0; i < list.size(); i++) {
            BookComment bookComment = list.get(i);
            String message = messageService.getMessage("bookcomment.ID.output")
                    + String.valueOf(bookComment.getBookCommentId()) + "; "
                    + messageService.getMessage("bookcomment.comment.output")
                    + bookComment.getComment();
            inputOutputService.printOut(message);
        }

    }
}
