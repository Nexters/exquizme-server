package com.exquizme.domain.quiz;

import com.exquizme.domain.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by garinkim on 2017. 8. 4..
 */
@Transactional(readOnly = true)
@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    // 단일 퀴즈 생성
    public Quiz makeQuiz(Quiz paramQuiz) {
        Quiz quiz = new Quiz();
        quiz.setId(paramQuiz.getId());
        quiz.setText(paramQuiz.getText());
        quiz.setType(paramQuiz.getType());
        quiz.setUser(paramQuiz.getUser());
        quizRepository.save(quiz);
        return quiz;
    }


}
