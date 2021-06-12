package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.BookAlreadyExistsException;
import ru.otus.spring.exception.BookNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long bookID       = resultSet.getLong("bookID");
            String title      = resultSet.getString("title");
            Long genreID      = resultSet.getLong("genre.GenreID");
            String genreName  = resultSet.getString("genre.Name");
            Long authorID     = resultSet.getLong("author.AuthorID");
            String authorName = resultSet.getString("author.Name");
            return new Book(bookID, title, new Author(authorID, authorName), new Genre(genreID, genreName));
        }
    }

    @Override
    public Book insert(Book book) throws BookAlreadyExistsException {
        if (checkExists(book)){
            throw new BookAlreadyExistsException(" Книга " + book.getTitle() + " уже добавлена в базу!");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("genreID", book.getGenre().getGenreID());
        params.addValue("authorID", book.getAuthor().getAuthorID());
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update(
                "insert into book (title, genreID, authorID) values (:title, :genreID, :authorID)", params, key
        );
        book.setBookID((Long) key.getKey());

        return book;
    }

    @Override
    public void update(Book book)  throws BookNotFoundException {
        if (!checkExists(book)) {
            throw new BookNotFoundException(" Книга с ID="+ book.getBookID() +" не найдена в базе!");
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("bookID", book.getBookID());
        params.addValue("title", book.getTitle());
        params.addValue("genreID", book.getGenre().getGenreID());
        params.addValue("authorID", book.getAuthor().getAuthorID());

        namedParameterJdbcOperations.update(
                "update book set title = :title, genreID = :genreID, authorID = :authorID where bookID = :bookID", params
        );
    }

    @Override
    public void delete(Book book)  throws BookNotFoundException {
        if (!checkExists(book)){
            throw new BookNotFoundException(" Книга с ID="+ book.getBookID() +" не найдена в базе!");
        }

        Map<String, Object> params = Collections.singletonMap("bookID", book.getBookID());
        namedParameterJdbcOperations.update(
                "delete from book where bookID = :bookID", params
        );
    }

    @Override
    public List<Book> findAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query(
                "select book.bookID, book.title, author.name, author.authorID, genre.name, genre.genreID\n" +
                        "from book \n" +
                        "inner join author\n" +
                        "on author.authorID = book.authorID\n" +
                        "inner join genre\n" +
                        "on genre.genreID = book.genreID", new BookMapper()
        );
    }

    @Override
    public Optional<Book> findByID(Long bookID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bookID", bookID);
        Book res = namedParameterJdbcOperations.queryForObject(
                "select book.bookID, book.title, author.name, author.authorID, genre.name, genre.genreID\n" +
                        "from book \n" +
                        "inner join author on author.authorID = book.authorID\n" +
                        "inner join genre on genre.genreID = book.genreID\n"+
                        "where bookID = :bookID", params, new BookMapper()
        );
        return Optional.ofNullable(res);
    }

    @Override
    public List<Book> findBookByParam(Long authorID, Long genreID, String title) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("title", title);
        params.put("genreID", genreID);
        params.put("authorID", authorID);
        List<Book> res = namedParameterJdbcOperations.query(
                "select book.bookID, book.title, author.name, author.authorID, genre.name, genre.genreID\n" +
                        "from book \n" +
                        "inner join author on author.authorID = book.authorID\n" +
                        "inner join genre on genre.genreID = book.genreID\n"+
                        "where book.title = :title and book.genreID = :genreID and book.authorID = :authorID", params,  new BookMapper());
        return res;
    }

    @Override
    public boolean checkExists(Book book) {
        Integer res = 0;
        MapSqlParameterSource params = new MapSqlParameterSource();
        if (book.getBookID() != 0){
            params.addValue("bookID", book.getBookID());
            res =  namedParameterJdbcOperations.queryForObject(
                    "select count(*) from book where bookID = :bookID", params, Integer.class
            );
        } else {
            params.addValue("title", book.getTitle());
            params.addValue("genreID", book.getGenre().getGenreID());
            params.addValue("authorID", book.getAuthor().getAuthorID());
            res =  namedParameterJdbcOperations.queryForObject(
                    "select count(*) from book where title = :title and genreID = :genreID and authorID = :authorID", params, Integer.class
            );
        }
        return res>0;
    }
}

