package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.BookNotFoundException;
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
            Long genreID  = resultSet.getLong("genreID");
            String name    = resultSet.getString("name");
            return new Genre(genreID, name);
        }
    }


    @Override
    public Genre insert(Genre genre) throws GenreAlreadyExistsException {
        if (checkExists(genre)){
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
        if (!checkExists(genre)) {
            throw new GenreNotFoundException(" Жанр с ID="+ genre.getName() +" не найден в базе!");
        }
        if (checkExists(genre)){
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
        if (!checkExists(genre)) {
            throw new GenreNotFoundException(" Жанр с ID="+ genre.getName() +" не найден в базе!");
        }
        if (checkExists(genre)){
            Map<String, Object> params = Collections.singletonMap("genreID", genre.getGenreID());
            namedParameterJdbcOperations.update(
                    "delete from genre where genreID = :genreID", params
            );
        }
    }

    @Override
    public List<Genre> findAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query(
                "select * from genre", new GenreMapper()
        );
    }

    @Override
    public Optional<Genre> findByID(Long genreID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("genreID", genreID);
        Genre res = namedParameterJdbcOperations.queryForObject(
                "select * from genre where genreID = :genreID", params, new GenreMapper()
        );
        return Optional.ofNullable(res);
    }

    @Override
    public List<Genre> findByName(String name) {
        String sql = String.format("select * from genre where name = '%s'", name);
        List<Genre> res = namedParameterJdbcOperations.getJdbcOperations().query(sql, new GenreMapper());
        return res;
    }

    @Override
    public boolean checkExists(Genre genre) {
        int res = 0;
        MapSqlParameterSource params = new MapSqlParameterSource();
        if (genre.getGenreID() > 0 ){
            params.addValue("genreID", genre.getGenreID());
            res =  namedParameterJdbcOperations.queryForObject(
                    "select count(*) from genre where genreID = :genreID", params, Integer.class
            );
        } else {
            params.addValue("name", genre.getName());
            res =  namedParameterJdbcOperations.queryForObject(
                    "select count(*) from genre where name = :name", params, Integer.class
            );
        }
        return res>0;
    }
}
