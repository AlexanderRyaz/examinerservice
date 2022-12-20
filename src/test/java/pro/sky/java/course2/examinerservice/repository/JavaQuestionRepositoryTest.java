package pro.sky.java.course2.examinerservice.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(JavaQuestionRepository.class)
class JavaQuestionRepositoryTest {
    @Autowired
    private JavaQuestionRepository repository;

    @ParameterizedTest
    @MethodSource("prepareDataForAddTest")
    void add(String question, String answer, Question q) {
        repository.add("что такое инкапсуляция",
                "объединение данных и методов работы с этими данными в одной упаковке");
        Question actualQuestion = repository.add(question, answer);
        assertEquals(q, actualQuestion);
    }

    public static Stream<Arguments> prepareDataForAddTest() {
        return Stream.of(
                Arguments.of("что такое конструктор", "метод для создания обьекта",
                        new Question("что такое конструктор", "метод для создания обьекта")),
                Arguments.of("что такое инкапсуляция", "объединение данных " +
                        "и методов работы с этими данными в одной упаковке", null)
        );
    }

    @ParameterizedTest
    @MethodSource("prepareDataForRemove")
    void remove(Question toDelete, Question expectedQuestion) {
        repository.add("что такое String", "класс, который представляет строку");
        Question actualQuestion = repository.remove(toDelete);
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
        repository.add("что такое наследование",
                "механизм, который позволяет создавать классы на основе других классов");
        Collection<Question> actualQuestions = repository.getAll();
        assertNotNull(actualQuestions);
        assertNotEquals(0, actualQuestions.size());
    }

    @Test
    void getRandomQuestion() {
        Question question1 = repository.add("что такое int", "целочисленный тип");
        Question question2 = repository.add("что такое double", "дробный тип");
        Question question3 = repository.add("что такое char", "символьный тип");
        Question q1 = repository.getRandomQuestion();
        assertNotNull(q1);
        assertTrue(List.of(question1, question2, question3).contains(q1));
        Question q2 = repository.getRandomQuestion();
    }
}