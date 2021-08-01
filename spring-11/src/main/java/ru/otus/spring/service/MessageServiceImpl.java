package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppSettings;

@Service
public class MessageServiceImpl implements MessageService{

    private final InputOutputService inputOutputService;
    private final MessageSource messageSource;
    private final AppSettings settings;

    public MessageServiceImpl(InputOutputService inputOutputService, MessageSource messageSource, AppSettings settings) {
        this.inputOutputService = inputOutputService;
        this.messageSource = messageSource;
        this.settings = settings;
    }

    @Override
    public void messagePrintOut(String messageID) {
        inputOutputService.printOut(messageSource.getMessage(messageID,null, settings.getLocale()));
    }

    @Override
    public void messagePrintOut(String messageID, String message) {
        inputOutputService.printOut(messageSource.getMessage(messageID,null, settings.getLocale()) + message);
    }

    @Override
    public void messagePrintOut(String messageID, Object[] objects) {
        inputOutputService.printOut(messageSource.getMessage(messageID, objects, settings.getLocale()));
    }

    @Override
    public String getMessage(String messageID) {
        return messageSource.getMessage(messageID,null, settings.getLocale());
    }
}
