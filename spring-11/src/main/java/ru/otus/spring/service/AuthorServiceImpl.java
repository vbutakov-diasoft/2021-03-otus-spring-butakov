package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.domain.Author;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final MessageService messageService;
    private final InputOutputService inputOutputService;

    public AuthorServiceImpl(AuthorRepository authorRepository, MessageService messageService, InputOutputService inputOutputService) {
        this.authorRepository = authorRepository;
        this.messageService = messageService;
        this.inputOutputService = inputOutputService;
    }

    @Transactional
    @Override
    public void insert() {
        messageService.messagePrintOut("author.name.input");
        String name = inputOutputService.readString();
        Author author = new Author(0L, name);
        authorRepository.save(author);
    }

    @Transactional
    @Override
    public void update() {
        messageService.messagePrintOut("author.ID.input");
        Long id = inputOutputService.readLong();
        messageService.messagePrintOut("author.name.input");
        String name = inputOutputService.readString();
        Author author = new Author(id, name);
        authorRepository.save(author);
    }

    @Transactional
    @Override
    public void delete() {
        messageService.messagePrintOut("author.ID.input");
        Long id = inputOutputService.readLong();
        Author author = new Author(id, "");
        authorRepository.delete(author);
    }

    @Transactional(readOnly=true)
    @Override
    public void findAll() {
        List<Author> list = authorRepository.findAll();
        if (list.size() == 0) {
            messageService.messagePrintOut("author.list.empty");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Author author = list.get(i);
            String message = messageService.getMessage("author.ID.output")
                    + String.valueOf(author.getAuthorID()) + "; "
                    + messageService.getMessage("author.name.output")
                    + author.getName();
            inputOutputService.printOut(message);
        }
    }
}
