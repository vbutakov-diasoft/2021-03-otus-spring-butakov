package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class InputOutputLocalizationServiceImpl implements InputOutputLocalizationService {
    private final InputOutputService inputOutputService;
    private final LocalizationService localizationService;

    public InputOutputLocalizationServiceImpl(InputOutputService inputOutputService, LocalizationService localizationService) {
        this.inputOutputService = inputOutputService;
        this.localizationService = localizationService;
    }


    @Override
    public void printOut(String st) {
        inputOutputService.printOut(st);
    }

    @Override
    public void printLocalOut(String st, String... args) {
        inputOutputService.printOut(localizationService.getLocalizedString(st,args));
    }

    @Override
    public String readString() {
        return inputOutputService.readString();
    }

    @Override
    public int readInt() {
        return inputOutputService.readInt();
    }
}
