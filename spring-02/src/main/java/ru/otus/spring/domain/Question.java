package ru.otus.spring.domain;

import java.io.IOException;

public class Question {
    private final String   question;           //  вопрос
    private final String[] possibleAnswer;    // варианты ответов
    private final int      rightAnswerNumber; // номер правильного ответа

    public Question(String defintion, String[] possibleAnswer, int rightAnswerNumber) throws IOException {
        if ((defintion == null)|(possibleAnswer[2] == null)|(rightAnswerNumber==0)){
            throw new IOException();
        }
        this.question         = defintion;
        this.possibleAnswer    = possibleAnswer;
        this.rightAnswerNumber = rightAnswerNumber;
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
