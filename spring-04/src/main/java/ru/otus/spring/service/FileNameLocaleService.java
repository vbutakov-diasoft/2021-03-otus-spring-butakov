package ru.otus.spring.service;

import ru.otus.spring.config.AppSettings;

public class FileNameLocaleService {
    private final String fileNameLocale;
    private final AppSettings appSettings;

    public FileNameLocaleService(AppSettings appSettings) {
        this.appSettings = appSettings;
        this.fileNameLocale = appSettings.getFileName() +"_"+ appSettings.getLocale().toString() + ".csv";
    }

    public String getFileNameLocale() {
        return fileNameLocale;
    }
}
