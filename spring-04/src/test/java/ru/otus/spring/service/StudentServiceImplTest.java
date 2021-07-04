package ru.otus.spring.service;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.context.MessageSource;

import ru.otus.spring.domain.Student;

@DisplayName("Класс StudentServiceImpl")
class StudentServiceImplTest {

    private static final String STUDENT_NAME = "Иванов";
    private static final String ASK_NAME = "ФИО студента";

    @Test
    @DisplayName("Спросить имя студента")
    void askStudentNameTest() {
        InputOutputService ioMock = mock(InputOutputService.class);
        MessageSource messageMock = mock(MessageSource.class);
        StudentService studentServiceImpl = new StudentServiceImpl(ioMock, messageMock);
        Mockito.when(ioMock.readString()).thenReturn(STUDENT_NAME);
        Mockito.when(messageMock.getMessage(Mockito.anyString(), new Object[]{Mockito.any()}, Mockito.any(Locale.class))).thenReturn(ASK_NAME);
        Mockito.doNothing().when(ioMock).printOut(Mockito.anyString());

        Student student = studentServiceImpl.askStudentName();
        assertThat(student)
                .hasFieldOrPropertyWithValue(ASK_NAME, STUDENT_NAME);
    }
}