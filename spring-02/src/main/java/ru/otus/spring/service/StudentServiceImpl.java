package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Student;

@Service
public class StudentServiceImpl implements StudentService{

    private final InputOutputService ioService;

    @Autowired
    public StudentServiceImpl (InputOutputService ioService) {
        this.ioService = ioService;
    }

    public Student askStudentName () {
        ioService.printOut("Student's Name:");
        return new Student(ioService.readString());
    }

}
