package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.exception.SourceLoadingException;

@ShellComponent
@RequiredArgsConstructor
public class ExamShell {

    private final ShellService shellService;

    @ShellMethod(value = "Start testing", key = {"Start testing", "st"})
    public void testStart() throws QuestionsLoadingException, SourceLoadingException {
        shellService.testStart();
    }
}
