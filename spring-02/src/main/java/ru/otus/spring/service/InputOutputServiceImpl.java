package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

    private final InputStream inputStream;
    private final PrintStream printStream;


    public InputOutputServiceImpl(@Value("#{ T(System).in}") InputStream inputStream, @Value("#{ T(System).out}") PrintStream printStream){
        this.inputStream = inputStream;
        this.printStream = printStream;
    }

    public void printOut(String st) {
        printStream.println(st);
    }

    public String readString() {
        return new Scanner(inputStream).nextLine();
    }

    public int readInt() {
        return new Scanner(inputStream).nextInt();
    }
}
