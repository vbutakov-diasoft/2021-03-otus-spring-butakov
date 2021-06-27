package ru.otus.spring.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private Long bookID;
    private final String title;
    private final Author author;
    private final Genre genre;

    public Book (Long bookID, String title, Author author, Genre genre){
        this.bookID = bookID;
        this.title  = title;
        this.author = author;
        this.genre  = genre;
    }
}
