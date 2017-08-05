package com.exquizme;

import com.exquizme.domain.quiz.Quiz;
import com.exquizme.domain.quiz.QuizController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExquizmeApplicationTests {

	@Autowired
	private QuizController quizController;
	@Test
	public void contextLoads() {
	}

}
