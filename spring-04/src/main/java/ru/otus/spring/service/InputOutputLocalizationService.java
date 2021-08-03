package ru.otus.spring.service;

public interface InputOutputLocalizationService {

    void printOut(String st);
    void printLocalOut(String st, String... args);
    String readString() ;
    int readInt() ;
}
