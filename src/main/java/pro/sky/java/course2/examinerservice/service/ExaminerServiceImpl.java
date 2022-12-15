package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    @Autowired
    private QuestionService questionService;

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount) {
            throw new QuestionException("столько вопросов нет!!!");
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() != amount) {
            Question randomQuestion = questionService.getRandomQuestion();
            questions.add(randomQuestion);
        }
        return questions;
    }
}
