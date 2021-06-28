package ru.otus.spring.service;

import org.springframework.stereotype.Service;
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

    @Override
    public void insert() {
        messageService.messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        Genre genre = new Genre(0L, name);
        try {
            genreDao.insert(genre);
            messageService.messagePrintOut("genre.success.insert", new Object[]{genre.getGenreID(), genre.getName()});
        } catch (GenreAlreadyExistsException e) {
            messageService.messagePrintOut("genre.error.insert", e.getMessage());
        }
    }

    @Override
    public void update() {
        messageService.messagePrintOut("genre.ID.input");
        Long id = inputOutputService.readLong();
        inputOutputService.readString();
        messageService.messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        Genre genre = new Genre(id, name);
        if (genre != null) {
            try {
                genreDao.update(genre);
                messageService.messagePrintOut("genre.success.update", new Object[]{genre.getGenreID(), genre.getName()});
            } catch (GenreNotFoundException e) {
                messageService.messagePrintOut("genre.error.update", e.getMessage());
            }
        } else {
            messageService.messagePrintOut("genre.error.update");
        }
    }

    @Override
    public void delete() {
        messageService.messagePrintOut("genre.ID.input");
        Long id = inputOutputService.readLong();
        Genre genre = new Genre(id, "");
        if (genre.getGenreID() > 0) {
            try {
                genreDao.delete(genre);
                messageService.messagePrintOut("genre.success.delete", new Object[]{genre.getGenreID()});
            } catch (GenreNotFoundException e) {
                messageService.messagePrintOut("genre.error.delete", e.getMessage());
            }
        } else {
            messageService.messagePrintOut("genre.error.delete");
        }
    }

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
