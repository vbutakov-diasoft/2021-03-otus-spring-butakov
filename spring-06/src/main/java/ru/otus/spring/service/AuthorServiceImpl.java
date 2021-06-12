package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorAlreadyExistsException;
import ru.otus.spring.exception.AuthorNotFoundException;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final ShellService shellService;
    private final MessageService messageService;

    public AuthorServiceImpl(AuthorDao authorDao, ShellService shellService, MessageService messageService) {
        this.authorDao = authorDao;
        this.shellService = shellService;
        this.messageService = messageService;
    }

    @Override
    public void insert() {
        Author author = shellService.authorInsert();
        if (author != null){
            try {
                authorDao.insert(author);
                messageService.messagePrintOut("author.success.insert", new Object[] {author.getAuthorID(), author.getName()});
            } catch (AuthorAlreadyExistsException e) {
                messageService.messagePrintOut("author.error.insert", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.insert");
        }
    }

    @Override
    public void update() {
        Author author = shellService.authorUpdate();
        if (author != null){
            try {
                authorDao.update(author);
                messageService.messagePrintOut("author.success.update", new Object[] {author.getAuthorID(), author.getName()});
            } catch (AuthorNotFoundException e) {
                messageService.messagePrintOut("author.error.update", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.update");
        }
    }

    @Override
    public void delete() {
        Author author = shellService.authorDelete();
        if (author.getAuthorID() > 0){
            try {
                authorDao.delete(author);
                messageService.messagePrintOut("author.success.delete", new Object[] {author.getAuthorID()});
            } catch (AuthorNotFoundException e) {
                messageService.messagePrintOut("author.error.delete", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("author.error.delete");
        }
    }

    @Override
    public void findAll() {
        List<Author> list = authorDao.findAll();
        shellService.authorListOutput(list);
    }
}
