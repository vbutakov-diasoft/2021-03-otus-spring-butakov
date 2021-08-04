package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.service.QuestionFileNameProvider;

@Configuration
public class AppConfig {
    @Bean
    QuestionDaoCsv questionDaoCsv(AppSettings settings) {
        QuestionFileNameProvider questionFileNameProvider = new QuestionFileNameProvider(settings.getFileName(),settings.getLocale().toString());
        return new QuestionDaoCsv(questionFileNameProvider.getQuestionFileName());
    }
}
