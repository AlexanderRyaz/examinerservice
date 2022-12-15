package pro.sky.java.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("")
public class ExamController {
    @Autowired
    private ExaminerService examinerService;
    @GetMapping
    public Collection<Question> getQuestions(@RequestParam("amount") int amount){
        return examinerService.getQuestions(amount);
    }
}
