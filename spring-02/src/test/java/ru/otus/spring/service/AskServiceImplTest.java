package ru.otus.spring.service;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.context.MessageSource;

import ru.otus.spring.domain.Student;

@DisplayName("Класс AskServiceImpl")
class AskServiceImplTest {

    @Test
    @DisplayName("Спросить имя студента")
    void askStudentNameTest() {
        InputOutputService ioMock = mock(InputOutputService.class);
        AskService askService = new AskServiceImpl(ioMock);
        Mockito.when(ioMock.readString()).thenReturn("Виктор");
        Mockito.doNothing().when(ioMock).printOut(Mockito.anyString());

        Student student = askService.askStudentName();
        assertThat(student)
                .hasFieldOrPropertyWithValue("name", "Виктор");
    }
}