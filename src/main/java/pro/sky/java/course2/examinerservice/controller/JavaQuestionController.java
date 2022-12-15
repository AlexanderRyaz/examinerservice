package pro.sky.java.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    @Autowired
    private QuestionService service;

    @PostMapping("add")
    public Question addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
       return service.add(question, answer);
    }
    @GetMapping
    public Collection<Question>getQuestions(){
        return service.getAll();
    }
    @DeleteMapping("remove")
    public Question removeQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer){
        Question q = new Question(question, answer);
        return service.remove(q);
    }
}
