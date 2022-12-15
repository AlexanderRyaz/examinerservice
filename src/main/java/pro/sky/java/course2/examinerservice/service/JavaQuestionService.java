package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.*;
@Service
public class JavaQuestionService implements QuestionService {
    private Set<Question> questions = new HashSet<>();
    private Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        if (questions.add(q)) {
            return q;
        } else {
            return null;
        }
    }

    @Override
    public Question add(Question question) {
        if (questions.add(question)) {
            return question;
        } else {
            return null;
        }
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(questions.size());
        List<Question> questionList = new ArrayList<>(questions);
        return questionList.get(index);
    }
}
