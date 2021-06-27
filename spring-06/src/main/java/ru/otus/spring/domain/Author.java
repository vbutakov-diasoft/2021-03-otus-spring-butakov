package ru.otus.spring.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {

    private Long authorID;
    private final String name;

    public Author(Long authorID, String name) {
        this.authorID = authorID;
        this.name = name;
    }
}
