package ru.otus.spring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookCommentDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.exception.BookCommentNotFoundException;

import java.util.List;
import java.util.Optional;

public class BookCommentServiceImpl implements BookCommentService {

    private final BookDao bookDao;
    private final MessageService messageService;
    private final InputOutputService inputOutputService;
    private final BookCommentDao bookCommentDao;

    public BookCommentServiceImpl(BookDao bookDao, MessageService messageService, InputOutputService inputOutputService, BookCommentDao bookCommentDao) {
        this.bookDao = bookDao;
        this.messageService = messageService;
        this.inputOutputService = inputOutputService;
        this.bookCommentDao = bookCommentDao;
    }

    @Override
    public void insert() {
        Long bookID = bookIDInput();
        Optional<Book> book = bookDao.findByID(bookID);
        if (!book.isPresent()){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        String remark = bookCommentCommentInput();
        bookCommentDao.insert(new BookComment(0L, book.get(), remark));
    }

    @Override
    public void update() throws BookCommentNotFoundException {
        Long bookID = bookIDInput();
        Optional<Book> book = bookDao.findByID(bookID);
        if (!book.isPresent()){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        List<BookComment> list = bookCommentDao.findByBookId(bookID);
        bookCommentListOutput(list);
        Long commentID = bookCommentIDInput();
        Optional<BookComment> bookComment = bookCommentDao.findByID(commentID);
        if (!bookComment.isPresent()){
            messageService.messagePrintOut("bookcomment.error.coomentNotFound");
            return;
        }
        String comment = bookCommentCommentInput();
        bookComment.get().setComment(comment);
        bookCommentDao.update(bookComment.get());
    }

    @Override
    public void delete() throws BookCommentNotFoundException {
        Long bookID = bookIDInput();
        Optional<Book> book = bookDao.findByID(bookID);
        if (!book.isPresent()){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        List<BookComment> list = bookCommentDao.findByBookId(bookID);
        bookCommentListOutput(list);
        Long commentID = bookCommentIDInput();
        Optional<BookComment> comment = bookCommentDao.findByID(commentID);
        if (!comment.isPresent()){
            messageService.messagePrintOut("bookcomment.error.coomentNotFound");
            return;
        }
        bookCommentDao.delete(comment.get());
    }

    @Override
    @Transactional(readOnly = true)
    public void findByBook() {
        Long bookID = bookIDInput();
        Optional<Book> book = bookDao.findByID(bookID);
        if (!book.isPresent()){
            messageService.messagePrintOut("book.error.bookNotFound");
            return;
        }
        List<BookComment> list = bookCommentDao.findByBookId(bookID);
        bookCommentListOutput(list);
    }

    @Override
    public Long bookIDInput() {
        messageService.messagePrintOut("book.ID.input");
        Long bookID = inputOutputService.readLong();
        inputOutputService.readString();
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
