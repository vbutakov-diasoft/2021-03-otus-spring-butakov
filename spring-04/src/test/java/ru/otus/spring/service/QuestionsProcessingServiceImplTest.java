package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.spring.domain.Question;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DisplayName("Класс QuestionsProcessingServiceImpl")
public class QuestionsProcessingServiceImplTest {
    private static final int QUESTIONNUMBER = 1;
    private static final String DEFINITION = "2+2";
    private static final String[] POSSIBLEANSWER = {"3", "4", "5"};
    private static final int RIGHTANSWERNUMBER = 2;

    @Test
    @DisplayName("должен вернуть true, если ответ на вопрос верный")
    void askStudentNameTest() throws IOException {
        InputOutputLocalizationService ioMock = mock(InputOutputLocalizationService.class);
        Question question = new Question(QUESTIONNUMBER,DEFINITION, POSSIBLEANSWER,RIGHTANSWERNUMBER);

        QuestionsProcessingServiceImpl questionsProcessingService = new QuestionsProcessingServiceImpl(ioMock);
        Mockito.when(ioMock.readInt()).thenReturn(RIGHTANSWERNUMBER);

        boolean answerIs = questionsProcessingService.askQuestion(question);
        assertThat(answerIs).isTrue();

    }
}
