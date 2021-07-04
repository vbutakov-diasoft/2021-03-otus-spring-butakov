package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService {

    private final PrintStream printStream;

    private final Scanner scanner;

    public InputOutputServiceImpl(@Value("#{ T(System).in}") InputStream inputStream, @Value("#{ T(System).out}") PrintStream printStream) {
        this.printStream = printStream;
        scanner = new Scanner(inputStream);
    }

    public void printOut(String st) {
        printStream.println(st);
    }

    public String readString() {
        return scanner.nextLine();
    }

    public int readInt() {
        return scanner.nextInt();
    }
}
