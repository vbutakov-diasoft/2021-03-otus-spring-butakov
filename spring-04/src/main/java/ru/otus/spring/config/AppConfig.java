package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.dao.QuestionDaoCsv;
import ru.otus.spring.service.FileNameLocaleService;

@Configuration
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    QuestionDaoCsv questionDaoSimple(AppSettings settings) {
        FileNameLocaleService fileNameLocale = new FileNameLocaleService(settings.getFileName(),settings.getLocale());
        return new QuestionDaoCsv(fileNameLocale, settings.getLocale());
    }
}
