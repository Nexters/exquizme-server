package com.exquizme.domain.quiz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@Slf4j
@RestController
public class QuizController {

    @GetMapping("/quizzes")
    public String helloFacebook(Model model) {
        return "user";
    }
}
