package pro.sky.java.course2.examinerservice.repository;

import org.springframework.stereotype.Component;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.*;

@Component
public class JavaQuestionRepository implements QuestionRepository {
    private Set<Question> questions = new HashSet<>();
    private Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        return add(q);
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
