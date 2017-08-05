package com.exquizme.domain.quiz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by garinkim on 2017. 8. 5..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class QuizServiceTests {
    @Autowired
    private QuizService quizService;

    /**
     * Make user test.
     */
    @Test
    public void makeUserTest() {
        Quiz quiz = new Quiz();
        System.out.println("test");
    }
}
