package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.Main;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorAlreadyExistsException;
import ru.otus.spring.exception.AuthorNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с авторами")
@Import({AuthorDaoJpa.class})
@ContextConfiguration(classes= Main.class)
@DataJpaTest
class AuthorDaoJpaTest {

    private static final Long DEFAULT_AUTHOR_ID = 1L;
    private static final String DEFAULT_AUTHOR_NAME = "Mark Twain";

    private static final Long NEW_AUTHOR_ID = 2L;
    private static final String NEW_AUTHOR_NAME = "Jack London";

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("должен корректно добавлять в базу автора")
    void shouldCorrectInsertAuthor() throws AuthorAlreadyExistsException {
        Author author  = new Author(0L,  NEW_AUTHOR_NAME);
        authorDao.insert(author);

        Optional<Author> authorDB  = authorDao.findByID(NEW_AUTHOR_ID);

        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("authorID", NEW_AUTHOR_ID);
        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", NEW_AUTHOR_NAME);
    }

    @Test
    @DisplayName("должен корректно удалять из базы автора")
    void shouldCorrectDeleteAuthorByID() throws AuthorNotFoundException {
        Author author  = new Author(DEFAULT_AUTHOR_ID,  DEFAULT_AUTHOR_NAME);
        authorDao.delete(author);
        assertThat(authorDao.checkExistsByID(author)).isFalse();
    }

    @Test
    @DisplayName("должен корректно искать автора в базе по ID")
    void shouldCorrectFindAuthorByID() {
        Optional<Author> authorDB = authorDao.findByID(DEFAULT_AUTHOR_ID);
        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("authorID", DEFAULT_AUTHOR_ID);
        assertThat(authorDB).isNotEmpty().get().hasFieldOrPropertyWithValue("name", DEFAULT_AUTHOR_NAME);
    }
}