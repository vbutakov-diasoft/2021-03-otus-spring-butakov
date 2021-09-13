package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repository.GenreRepository;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final MessageService messageService;
    private final InputOutputService inputOutputService;

    public GenreServiceImpl(GenreRepository genreRepository, MessageService messageService, InputOutputService inputOutputService) {
        this.genreRepository = genreRepository;
        this.inputOutputService = inputOutputService;
        this.messageService = messageService;
    }

    @Transactional
    @Override
    public void insert() {
        messageService.messagePrintOut("genre.name.input");
        String name = inputOutputService.readString();
        Genre genre = new Genre(0L, name);
        genreRepository.save(genre);
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
        genreRepository.save(genre);
        messageService.messagePrintOut("genre.success.update", new Object[]{genre.getGenreID(), genre.getName()});
    }

    @Transactional
    @Override
    public void delete() {
        messageService.messagePrintOut("genre.ID.input");
        Long id = inputOutputService.readLong();
        Genre genre = new Genre(id, "");
        genreRepository.delete(genre);
        messageService.messagePrintOut("genre.success.delete", new Object[]{genre.getGenreID()});
    }

    @Transactional(readOnly = true)
    @Override
    public void findAll() {
        List<Genre> list = genreRepository.findAll();
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
