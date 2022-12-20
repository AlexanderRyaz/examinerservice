package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.Collection;

@Service
public class JavaQuestionService implements QuestionService {
    @Qualifier("javaQuestionRepository")
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        return questionRepository.add(q);
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        return questionRepository.getRandomQuestion();
    }
}
