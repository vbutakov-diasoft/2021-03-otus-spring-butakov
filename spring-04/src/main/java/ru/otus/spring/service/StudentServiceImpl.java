package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppSettings;
import ru.otus.spring.domain.Student;

@Service
public class StudentServiceImpl implements StudentService{

    private final InputOutputService ioService;
    private final MessageSource messageSource;

    @Autowired
    private AppSettings settings;

    @Autowired
    public StudentServiceImpl(InputOutputService ioService, MessageSource messageSource) {
        this.ioService = ioService;
        this.messageSource = messageSource;
    }

    public Student askStudentName () {
        ioService.printOut(messageSource.getMessage("exam.name",null, settings.getLocale()));
        return new Student(ioService.readString());
    }

}
