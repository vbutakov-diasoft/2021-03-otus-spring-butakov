package ru.otus.spring.domain;

public class Caterpillar {
    private final boolean isEvolvingSuccess;

    public Caterpillar(boolean isEvolvingSuccess) {
        this.isEvolvingSuccess = isEvolvingSuccess;
    }

    public boolean isEvolvingSuccess() {
        return isEvolvingSuccess;
    }
}
