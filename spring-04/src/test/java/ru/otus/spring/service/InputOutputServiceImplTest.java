package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class InputOutputServiceImplTest {

    @Mock
    private PrintStream printStream;
    @Mock
    private InputStream inputStream;

    @InjectMocks
    private InputOutputServiceImpl inputOutputServiceImpl;

    private static final String ANY_STRING = "любая строка";

    @DisplayName("Должен выводить переданную строку")
    @Test
    public void shouldPrintString() {
        inputOutputServiceImpl.printOut(ANY_STRING);
        Mockito.verify(printStream).println(eq(ANY_STRING));
    }
}
