package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreAlreadyExistsException;
import ru.otus.spring.exception.GenreNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Genre insert(Genre genre) throws GenreAlreadyExistsException {
        if (checkExistsByName(genre)) {
            throw new GenreAlreadyExistsException("Жанр " + genre.getName() + " уже добавлен в базу!");
        }
        entityManager.persist(genre);
        return genre;
    }

    @Override
    public void update(Genre genre) throws GenreNotFoundException {
        if (!checkExistsByID(genre)) {
            throw new GenreNotFoundException(" Жанр с ID=" + genre.getName() + " не найден в базе!");
        } else {
            entityManager.merge(genre);
        }
    }

    @Override
    public void delete(Genre genre) throws GenreNotFoundException {
        if (!checkExistsByID(genre)) {
            throw new GenreNotFoundException(" Жанр с ID=" + genre.getName() + " не найден в базе!");
        } else {
            Query query = entityManager.createQuery("delete from Genre g where g.genreID = :genreID");
            query.setParameter("genreID", genre.getGenreID());
            query.executeUpdate();
        }
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery(
                "select g from Genre g",
                Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findByID(Long genreID) {
        return Optional.of(entityManager.find(Genre.class, genreID));
    }

    @Override
    public List<Genre> findByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery(
                "select g from Genre g where g.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public boolean checkExistsByID(Genre genre) {
        Long res = 0L;
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(g) from Genre g where g.genreID = :genreID",
                Long.class);
        query.setParameter("genreID", genre.getGenreID());
        res = query.getSingleResult();
        return res > 0;
    }

    @Override
    public boolean checkExistsByName(Genre genre) {
        Long res = 0L;
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(g) from Genre g where g.name = :name",
                Long.class);
        query.setParameter("name", genre.getName());
        res = query.getSingleResult();
        return res > 0;
    }
}
