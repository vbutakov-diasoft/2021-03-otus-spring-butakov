package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreAlreadyExistsException;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreDao genreDao;
    private final ShellService shellService;
    private final MessageService messageService;

    public GenreServiceImpl(GenreDao genreDao, ShellService shellService, MessageService messageService) {
        this.genreDao = genreDao;
        this.shellService = shellService;
        this.messageService = messageService;
    }

    @Override
    public void insert() {
        Genre genre = shellService.genreInsert();
        if (genre != null){
            try {
                genreDao.insert(genre);
                messageService.messagePrintOut("genre.success.insert", new Object[] {genre.getGenreID(), genre.getName()});
            } catch (GenreAlreadyExistsException e) {
                messageService.messagePrintOut("genre.error.insert", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.insert");
        }
    }

    @Override
    public void update() {
        Genre genre = shellService.genreUpdate();
        if (genre != null){
            try {
                genreDao.update(genre);
                messageService.messagePrintOut("genre.success.update", new Object[] {genre.getGenreID(), genre.getName()});
            }
            catch (GenreNotFoundException e) {
                messageService.messagePrintOut("genre.error.update", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.update");
        }
    }

    @Override
    public void delete() {
        Genre genre = shellService.genreDelete();
        if (genre.getGenreID() > 0){
            try {
                genreDao.delete(genre);
                messageService.messagePrintOut("genre.success.delete", new Object[]{genre.getGenreID()});
            }
            catch (GenreNotFoundException e) {
                messageService.messagePrintOut("genre.error.delete", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.delete");
        }
    }

    @Override
    public void findAll() {
        List<Genre> list = genreDao.findAll();
        shellService.genreListOutput(list);
    }

}
