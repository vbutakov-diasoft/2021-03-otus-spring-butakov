package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.service.FileNameLocaleService;

@Configuration
public class AppConfig {
    @Bean
    QuestionDaoCsv questionDaoCsv(AppSettings settings) {
        FileNameLocaleService fileNameLocale = new FileNameLocaleService(settings);
        return new QuestionDaoCsv(fileNameLocale.getFileNameLocale());
    }
}
