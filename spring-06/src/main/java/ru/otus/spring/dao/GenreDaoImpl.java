package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.GenreAlreadyExistsException;
import ru.otus.spring.exception.GenreNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Long genreID = resultSet.getLong("genreID");
            String name = resultSet.getString("name");
            return new Genre(genreID, name);
        }
    }


    @Override
    public Genre insert(Genre genre) throws GenreAlreadyExistsException {
        if (checkExistsByName(genre)) {
            throw new GenreAlreadyExistsException("Жанр " + genre.getName() + " уже добавлен в базу!");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into genre (`name`) values (:name)", params, key
        );
        genre.setGenreID((Long) key.getKey());

        return genre;
    }

    @Override
    public void update(Genre genre) throws GenreNotFoundException {
        if (!checkExistsByID(genre)) {
            throw new GenreNotFoundException(" Жанр с ID=" + genre.getName() + " не найден в базе!");
        }
        else {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("GenreID", genre.getGenreID());
            params.addValue("name", genre.getName());

            int res = namedParameterJdbcOperations.update(
                    "update genre set surName = :surName, name = :name where genreID = :genreID", params
            );
        }
    }

    @Override
    public void delete(Genre genre) throws GenreNotFoundException {
        if (!checkExistsByID(genre)) {
            throw new GenreNotFoundException(" Жанр с ID=" + genre.getName() + " не найден в базе!");
        }
        else {
            Map<String, Object> params = Collections.singletonMap("genreID", genre.getGenreID());
            namedParameterJdbcOperations.update(
                    "delete from genre where genreID = :genreID", params
            );
        }
    }

    @Override
    public List<Genre> findAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query(
                "select GENREID, NAME from genre", new GenreMapper()
        );
    }

    @Override
    public Optional<Genre> findByID(Long genreID) {
        final Map params = Collections.singletonMap("genreID", genreID);
        Genre res = namedParameterJdbcOperations.queryForObject(
                "select GENREID, NAME from genre where genreID = :genreID", params, new GenreMapper()
        );
        return Optional.ofNullable(res);
    }

    @Override
    public List<Genre> findByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        List<Genre> res = namedParameterJdbcOperations.query("select GENREID, NAME from genre where name = :name",params,new GenreMapper());
        return res;
    }

    @Override
    public boolean checkExistsByID(Genre genre) {
        int res = 0;
        final Map params = Collections.singletonMap("genreID", genre.getGenreID());
        res = namedParameterJdbcOperations.queryForObject(
                "select count(1) from genre where genreID = :genreID", params, Integer.class
        );
        return res > 0;
    }

    @Override
    public boolean checkExistsByName(Genre genre) {
        int res = 0;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", genre.getName());
        res = namedParameterJdbcOperations.queryForObject(
                "select count(1) from genre where name = :name", params, Integer.class
        );
        return res > 0;
    }
}
