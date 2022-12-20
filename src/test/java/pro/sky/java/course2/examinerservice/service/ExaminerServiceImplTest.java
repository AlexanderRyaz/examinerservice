package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest({JavaQuestionService.class, ExaminerServiceImpl.class})
class ExaminerServiceImplTest {
    @MockBean
    private JavaQuestionService javaQuestionService;
    @Autowired
    private ExaminerServiceImpl service;

    @Test
    void getQuestionsWithError() {
        when(javaQuestionService.getAll()).thenReturn(Set.of(new Question("вопрос", "ответ")));
        assertThrows(QuestionException.class, () -> service.getQuestions(15), "столько вопросов нет!!!");
    }

    @Test
    void getQuestions() {
        Question question1 = new Question("вопрос1", "ответ1");
        Question question2 = new Question("вопрос2", "ответ2");
        Question question3 = new Question("вопрос3", "ответ3");

        when(javaQuestionService.getAll()).thenReturn(Set.of(question1, question2, question3));
        when(javaQuestionService.getRandomQuestion()).thenReturn(question1).thenReturn(question1).thenReturn(question3);
        Collection<Question> actualQuestions = service.getQuestions(2);
        Set<Question> questionSet = new HashSet<>(actualQuestions);
        assertEquals(2,questionSet.size());
    }
}