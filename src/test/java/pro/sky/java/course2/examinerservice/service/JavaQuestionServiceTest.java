package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@WebMvcTest({JavaQuestionService.class, QuestionRepository.class})
class JavaQuestionServiceTest {
    @Autowired
    private JavaQuestionService service;
    @Qualifier("javaQuestionRepository")
    @MockBean
    private QuestionRepository repository;

    @ParameterizedTest
    @MethodSource("prepareDataForAddTest")
    void add(String question, String answer, Question q, Question questionFromRepository) {
//        when(repository.add(any(), any())).thenReturn(questionFromRepository);
        doReturn(questionFromRepository).when(repository).add(anyString(),anyString());
        Question actualQuestion = service.add(question, answer);
        assertEquals(q, actualQuestion);
    }

    public static Stream<Arguments> prepareDataForAddTest() {
        Question q1 = new Question("что такое конструктор", "метод для создания обьекта");
        return Stream.of(
                Arguments.of("что такое конструктор", "метод для создания обьекта",
                        q1, q1),
                Arguments.of("что такое инкапсуляция", "объединение данных " +
                        "и методов работы с этими данными в одной упаковке", null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("prepareDataForRemove")
    void remove(Question toDelete, Question expectedQuestion) {
        service.add("что такое String", "класс, который представляет строку");
        Question actualQuestion = service.remove(toDelete);
        assertEquals(expectedQuestion, actualQuestion);
    }

    public static Stream<Arguments> prepareDataForRemove() {
        Question q1 = new Question("что такое String", "класс, который представляет строку");
        return Stream.of(
                Arguments.of(q1, q1), Arguments.of(new Question
                        ("что такое абстракция", "класс в котром есть абстрактный метод"), null)

        );
    }

    @Test
    void getAll() {
        service.add("что такое наследование",
                "механизм, который позволяет создавать классы на основе других классов");
        Collection<Question> actualQuestions = service.getAll();
        assertNotNull(actualQuestions);
        assertNotEquals(0, actualQuestions.size());
    }

    @Test
    void getRandomQuestion() {
        Question question1 = service.add("что такое int", "целочисленный тип");
        Question question2 = service.add("что такое double", "дробный тип");
        Question question3 = service.add("что такое char", "символьный тип");
        Question q1 = service.getRandomQuestion();
        assertNotNull(q1);
        assertTrue(List.of(question1, question2, question3).contains(q1));
        Question q2 = service.getRandomQuestion();
    }
}