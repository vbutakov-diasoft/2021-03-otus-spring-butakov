package ru.otus.spring.service;

public interface BookService {
    void insert();
    void update();
    void delete();
    void findAll();

    String bookAuthorNameInput();
    String bookTitleInput();
}
