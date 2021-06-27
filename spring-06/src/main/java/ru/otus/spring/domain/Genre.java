package ru.otus.spring.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
    private Long genreID;
    private String name;

    public Genre(Long genreID, String name){
        this.genreID = genreID;
        this.name     = name;
    }
}
