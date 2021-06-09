package ru.otus.spring.domain;

import java.io.IOException;

public class Question {
    private final int questionNumber;    // номер вопроса
    private final String question;       // вопрос
    private final String[] possibleAnswer; // варианты ответов
    private final int rightAnswerNumber; // номер правильного ответа

    public Question(int questionNumber, String defintion, String[] possibleAnswer, int rightAnswerNumber) throws IOException {
        this.questionNumber = questionNumber;
        this.question = defintion;
        this.possibleAnswer = possibleAnswer;
        this.rightAnswerNumber = rightAnswerNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getPossibleAnswer() {
        return possibleAnswer;
    }

    public int getRightAnswerNumber() {
        return rightAnswerNumber;
    }
}
