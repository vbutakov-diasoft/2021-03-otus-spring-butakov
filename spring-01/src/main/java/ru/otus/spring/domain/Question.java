package ru.otus.spring.domain;

public class Question {
    private final String question;         // вопрос
    private final String[] possibleAnswer; // варианты ответов
    private final int rightAnswerNumber;   // номер правильного ответа

    public Question(String question, String[] possibleAnswer, int rightAnswerNumber) {
        this.question          = question;
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
