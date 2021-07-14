package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.Main;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreAlreadyExistsException;
import ru.otus.spring.exception.GenreNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с жанрами")
@ContextConfiguration(classes= Main.class)
@DataJpaTest
public class GenreRepositoryJpaTest {

    private static final Long DEFAULT_GENRE_ID = 1L;
    private static final String DEFAULT_GENRE_NAME = "Novel";

    private static final Long NEW_GENRE_ID = 2L;
    private static final String NEW_GENRE_NAME = "Comedy";

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("должен корректно добавлять в базу жанр")
    void shouldCorrectInsertGenre() throws GenreAlreadyExistsException {
        Genre genre  = new Genre(0L,  NEW_GENRE_NAME);
        genreRepository.save(genre);

        Optional<Genre> genreDB  = genreRepository.findById(NEW_GENRE_ID);

        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("genreID", NEW_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", NEW_GENRE_NAME);
    }

    @Test
    @DisplayName("должен корректно удалять из базы жанр")
    void shouldCorrectDeleteGenreByID() throws GenreNotFoundException {
        Genre genre  = new Genre(DEFAULT_GENRE_ID,  DEFAULT_GENRE_NAME);
        genreRepository.delete(genre);
        assertThat(genreRepository.existsById(DEFAULT_GENRE_ID)).isEqualTo(false);
    }

    @Test
    @DisplayName("должен корректно искать жанр в базе по ID")
    void shouldCorrectFindGenreByID() {
        Optional<Genre> genreDB = genreRepository.findById(DEFAULT_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("genreID", DEFAULT_GENRE_ID);
        assertThat(genreDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", DEFAULT_GENRE_NAME);
    }

}
