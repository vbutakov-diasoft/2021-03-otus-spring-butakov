package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Question;

import java.io.IOException;

@SpringBootTest
@DisplayName("Класс QuestionsProcessingServiceImpl")
class QuestionsProcessingServiceImplTest {
    private static final int QUESTIONNUMBER = 1;
    private static final String DEFINITION = "2+2";
    private static final String[] POSSIBLEANSWER = {"3", "4", "5"};
    private static final int RIGHTANSWERNUMBER = 2;

    @MockBean
    private InputOutputLocalizationService ioMock;

    @Autowired
    private QuestionsProcessingServiceImpl questionsProcessingService;

    @Test
    @DisplayName("должен вернуть true, если ответ на вопрос верный")
    void askStudentNameTest() throws IOException {
        Question question = new Question(QUESTIONNUMBER,DEFINITION, POSSIBLEANSWER,RIGHTANSWERNUMBER);

        Mockito.when(ioMock.readInt()).thenReturn(RIGHTANSWERNUMBER);

        boolean answerIs = questionsProcessingService.askQuestion(question);
        assertThat(answerIs).isTrue();

    }
}
