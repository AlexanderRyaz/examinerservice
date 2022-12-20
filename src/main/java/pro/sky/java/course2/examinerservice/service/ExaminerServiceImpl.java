package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    @Qualifier("javaQuestionService")
    @Autowired
    private QuestionService javaQuestionService;
    @Qualifier("mathQuestionService")
    @Autowired
    private QuestionService mathQuestionService;

    @Override
    public Collection<Question> getQuestions(int amount) {
        if ((javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) < amount) {
            throw new QuestionException("столько вопросов нет!!!");
        }
        Random random = new Random();
        int javaQuestionCount = random.nextInt(amount);
        int mathQuestionCount = amount - javaQuestionCount;
        Set<Question> questions = new HashSet<>();
        while (questions.size() != javaQuestionCount) {
            Question randomQuestion = javaQuestionService.getRandomQuestion();
            questions.add(randomQuestion);
        }
        while (questions.size() != mathQuestionCount) {
            Question randomQuestion = mathQuestionService.getRandomQuestion();
            questions.add(randomQuestion);
        }
        return questions;
    }
}
