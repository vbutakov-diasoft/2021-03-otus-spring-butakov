package ru.otus.spring.dao;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.SourceLoadingException;
import ru.otus.spring.exception.QuestionsLoadingException;

public final class QuestionDaoCsv implements QuestionDao {

    private final String fileNameLocale;

    public QuestionDaoCsv(String fileNameLocale) {
        this.fileNameLocale = fileNameLocale;
    }

    public List<Question> findAll() throws QuestionsLoadingException, SourceLoadingException {
        String localFileName = fileNameLocale;
        List<Question> questions = new LinkedList<>();
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(localFileName)))) {
            char separator = ';';
            CSVParser parser = new CSVParserBuilder().withSeparator(separator)
                    .build();
            String[] nextLine;
            int index = 0;

            while ((nextLine = csvReader.readNext()) != null) {
                for (String string : nextLine) {
                    String[] value;
                    value = parser.parseLine(string);

                    String[] answers = new String[3];
                    try {
                        answers[0] = value[2];
                        answers[1] = value[3];
                        answers[2] = value[4];
                        Question question = new Question(Integer.valueOf(value[0]), value[1], answers, Integer.valueOf(value[5]));
                        questions.add(question);
                        index++;
                    } catch (Exception e) {
                        throw new QuestionsLoadingException("Ошибка загрузки вопросов");
                    }
                }
            }
        } catch (Throwable  e) {
            throw new SourceLoadingException("Ошибка загрузки источника данных");
        }
        return questions;
    }

}
