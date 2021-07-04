package ru.otus.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.AuthorAlreadyExistsException;
import ru.otus.spring.exception.AuthorNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class AuthorDaoJpa implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author insert(Author author) throws AuthorAlreadyExistsException {
        if (checkExistsByName(author)) {
            throw new AuthorAlreadyExistsException("Автор " + author.getName() + " уже добавлен в базу!");
        }
        entityManager.persist(author);
        return author;
    }

    @Override
    public Author update(Author author) throws AuthorNotFoundException {
        if (!checkExistsByID(author)) {
            throw new AuthorNotFoundException("Автор c ID=" + author.getAuthorID() + " не найден в базе!");
        }
        return entityManager.merge(author);
    }

    @Override
    public void delete(Author author) throws AuthorNotFoundException {
        if (!checkExistsByID(author)) {
            throw new AuthorNotFoundException("Автор c ID=" + author.getAuthorID() + " не найден в базе!");
        }

        Query query = entityManager.createQuery("delete from Author a where a.authorID = :authorID");
        query.setParameter("authorID", author.getAuthorID());
        query.executeUpdate();
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a",
                Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findByID(Long authorID) {
        return Optional.of(entityManager.find(Author.class, authorID));
    }

    @Override
    public List<Author> findByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery(
                "select a from Author a where a.name = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public boolean checkExistsByName(Author author) {
        long res = 0L;
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(a) from Author a where a.name = :name",
                Long.class);
        query.setParameter("name", author.getName());
        res = query.getSingleResult();
        return res > 0;
    }

    @Override
    public boolean checkExistsByID(Author author) {
        long res = 0L;
        TypedQuery<Long> query = entityManager.createQuery(
                "select count(a) from Author a where a.authorID = :authorID",
                Long.class);
        query.setParameter("authorID", author.getAuthorID());
        res = query.getSingleResult();
        return res > 0;
    }
}