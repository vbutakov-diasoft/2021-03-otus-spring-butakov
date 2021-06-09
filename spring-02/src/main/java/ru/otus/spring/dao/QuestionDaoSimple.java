package ru.otus.spring.dao;

import java.io.InputStreamReader;
import java.util.LinkedList;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Question;

@Repository
public final class QuestionDaoSimple implements QuestionDao {

    private final LinkedList<Question> questions = new LinkedList<Question>();

    public QuestionDaoSimple(@Value("${file.name}") String fileName) {
        CSVReader csvReader;
        try {
            String local_file_name = fileName;
            csvReader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(local_file_name)));
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
                        System.out.println(e.getMessage());
                    }

                }
            }
            csvReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public LinkedList<Question> findAll() {
        return questions;
    }

}
