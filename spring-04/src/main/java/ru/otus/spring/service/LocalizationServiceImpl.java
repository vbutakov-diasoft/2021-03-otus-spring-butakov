package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppSettings;

@Service
public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private final AppSettings appSettings;

    public LocalizationServiceImpl(MessageSource messageSource, AppSettings appSettings) {
        this.messageSource = messageSource;
        this.appSettings = appSettings;
    }

    @Override
    public String getLocalizedString(String var, String... args) {
        return messageSource.getMessage(var, args, appSettings.getLocale());
    }
}
