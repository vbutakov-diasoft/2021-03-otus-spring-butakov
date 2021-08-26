package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Student;

@SpringBootTest
@DisplayName("Класс StudentServiceImpl")
class StudentServiceImplTest {

    private static final String STUDENT_NAME = "Иванов";

    @MockBean
    private InputOutputLocalizationService ioMock;

    @Autowired
    private StudentService studentServiceImpl;

    @Test
    @DisplayName("Спросить имя студента")
    void askStudentNameTest() {
        Mockito.when(ioMock.readString()).thenReturn(STUDENT_NAME);
        Mockito.doNothing().when(ioMock).printOut(Mockito.anyString());
        Mockito.doNothing().when(ioMock).printOut(Mockito.anyString());

        Student student = studentServiceImpl.askStudentName();
        assertThat(student)
                .hasFieldOrPropertyWithValue("name", STUDENT_NAME);

    }
}