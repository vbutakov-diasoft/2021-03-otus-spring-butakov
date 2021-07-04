package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.otus.spring.dao.QuestionDaoSimple;

@Configuration
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    QuestionDaoSimple questionDaoSimple(AppSettings settings) {
        return new QuestionDaoSimple(settings.getFileName(), settings.getLocale());
    }
}
