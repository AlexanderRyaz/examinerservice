package pro.sky.java.course2.examinerservice.repository;

import org.springframework.stereotype.Component;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;

@Component
public interface QuestionRepository {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
