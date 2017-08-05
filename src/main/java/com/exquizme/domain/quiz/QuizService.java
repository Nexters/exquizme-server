package com.exquizme.domain.quiz;

import com.exquizme.domain.quiz.option.QuizOption;
import com.exquizme.domain.quiz.option.QuizOptionDto;
import com.exquizme.domain.user.User;
import com.exquizme.domain.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

/**
 * Created by garinkim on 2017. 8. 4..
 */
@Transactional(readOnly = true)
@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    // 단일 퀴즈 생성
    @Transactional(readOnly = false)
    public Quiz createQuiz(QuizDto quizDto) {
        Quiz quiz = quizRepository.save(Quiz.builder()
        .text(quizDto.getText())
        .type(quizDto.getType())
        .user(quizDto.getUser())
        .build());
        return quiz;
    }

    @Transactional(readOnly = false)
    public QuizOption createQuizOption(QuizOptionDto quizOptionDto){
       return null;
    }



}
