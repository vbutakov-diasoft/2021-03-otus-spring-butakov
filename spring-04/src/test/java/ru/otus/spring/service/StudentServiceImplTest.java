package ru.otus.spring.service;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import ru.otus.spring.domain.Student;

@DisplayName("Класс StudentServiceImpl")
class StudentServiceImplTest {

    private static final String STUDENT_NAME = "Иванов";

    @Test
    @DisplayName("Спросить имя студента")
    void askStudentNameTest() {
        InputOutputService ioMock = mock(InputOutputService.class);
        StudentService studentServiceImpl = new StudentServiceImpl(ioMock);

        Mockito.when(ioMock.readString()).thenReturn(STUDENT_NAME);
        Mockito.doNothing().when(ioMock).printOut(Mockito.anyString());
        Mockito.doNothing().when(ioMock).printOut(Mockito.anyString());

        Student student = studentServiceImpl.askStudentName();
        assertThat(student)
                .hasFieldOrPropertyWithValue("name", STUDENT_NAME);

    }
}