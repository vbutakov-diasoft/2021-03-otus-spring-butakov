package ru.otus.spring.dao;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;

import ru.otus.spring.domain.Question;
import ru.otus.spring.exception.QuestionsLoadingException;
import ru.otus.spring.service.FileNameLocaleService;

public final class QuestionDaoCsv implements QuestionDao {

    private final FileNameLocaleService fileNameLocale;
    private final Locale locale;

    public QuestionDaoCsv(FileNameLocaleService fileNameLocale, Locale locale) {
        this.fileNameLocale = fileNameLocale;
        this.locale = locale;
    }

    public List<Question> findAll() throws QuestionsLoadingException {
        String localFileName = fileNameLocale.getFileNameLocale();
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
                        throw new QuestionsLoadingException("Ошибка загрузки файла");
                    }
                }
            }
        } catch (Throwable  e) {
            throw new QuestionsLoadingException("Ошибка чтения файла");
        }
        return questions;
    }

}
