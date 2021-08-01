package ru.otus.spring.service;

import java.util.Locale;

public class FileNameLocaleService {
    private final String fileNameLocale;

    public FileNameLocaleService(String fileNameLocale, Locale locale) {
        this.fileNameLocale = fileNameLocale +"_"+ locale.toString() + ".csv";
    }

    public String getFileNameLocale() {
        return fileNameLocale;
    }
}
