package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreAlreadyExistsException;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;
    private final MessageService messageService;
    private final InputOutputService inputOutputService;

    public GenreServiceImpl(GenreDao genreDao, MessageService messageService, InputOutputService inputOutputService) {
        this.genreDao = genreDao;
        this.inputOutputService = inputOutputService;
        this.messageService = messageService;
    }

    @Transactional
    @Override
    public void insert() {
        messageService.messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        Genre genre = new Genre(0L, name);
        genreDao.save(genre);
        messageService.messagePrintOut("genre.success.insert", new Object[]{genre.getGenreID(), genre.getName()});
    }

    @Transactional
    @Override
    public void update() {
        messageService.messagePrintOut("genre.ID.input");
        Long id = inputOutputService.readLong();
        inputOutputService.readString();
        messageService.messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        Genre genre = new Genre(id, name);
        genreDao.save(genre);
        messageService.messagePrintOut("genre.success.update", new Object[]{genre.getGenreID(), genre.getName()});
    }

    @Transactional
    @Override
    public void delete() {
        messageService.messagePrintOut("genre.ID.input");
        Long id = inputOutputService.readLong();
        Genre genre = new Genre(id, "");
        genreDao.delete(genre);
        messageService.messagePrintOut("genre.success.delete", new Object[]{genre.getGenreID()});
    }

    @Transactional(readOnly = true)
    @Override
    public void findAll() {
        List<Genre> list = genreDao.findAll();
        if (list.size() == 0) {
            messageService.messagePrintOut("genre.list.empty");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Genre genre = list.get(i);
            String message = messageService.getMessage("genre.ID.output")
                    + String.valueOf(genre.getGenreID()) + "; "
                    + messageService.getMessage("genre.name.output")
                    + genre.getName();
            inputOutputService.printOut(message);
        }
    }

}
