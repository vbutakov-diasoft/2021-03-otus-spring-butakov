package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.domain.Student;

import java.util.Locale;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("Класс AskServiceImpl")
class StudentServiceImplTest {

    private static final String MY_NAME = "Иванов";
    private static final String ASK_NAME = "ФИО студента";

    @MockBean
    private MessageSource messageMock;

    @MockBean
    private InputOutputService ioMock;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Test
    @DisplayName("Спросить имя студента")
    void askStudentNameTest() {
        Mockito.when(ioMock.readString()).thenReturn(MY_NAME);
        Mockito.when(messageMock.getMessage(Mockito.anyString(), Mockito.any(), Mockito.any(Locale.class))).thenReturn(ASK_NAME);

        Student student = studentServiceImpl.askStudentName();

        verify(ioMock, Mockito.times(1)).printOut(ASK_NAME);
        verify(messageMock, Mockito.times(1)).getMessage(Mockito.anyString(), Mockito.any(), Mockito.any());

        assertThat(student)
                .hasFieldOrPropertyWithValue("name", MY_NAME);
    }
}