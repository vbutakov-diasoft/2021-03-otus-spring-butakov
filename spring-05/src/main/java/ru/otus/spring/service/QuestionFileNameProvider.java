package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QuestionFileNameProvider {
    private final String questionFileName;

    public QuestionFileNameProvider(@Value("${application.filename}") String questionFileName, @Value("${application.locale}") String locale) {
        this.questionFileName = questionFileName+"_"+ locale + ".csv";
    }
    public String getQuestionFileName() {
        return questionFileName;
    }
}
