package com.exquizme.domain.quiz;

import com.exquizme.domain.quiz.answer.QuizAnswer;
import com.exquizme.domain.quiz.answer.QuizAnswerDto;
import com.exquizme.domain.quiz.answer.QuizAnswerRepository;
import com.exquizme.domain.quiz.option.QuizOption;
import com.exquizme.domain.quiz.option.QuizOptionDto;
import com.exquizme.domain.quiz.option.QuizOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by garinkim on 2017. 8. 4..
 */
@Transactional(readOnly = true)
@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizOptionRepository quizOptionRepository;

    @Autowired
    private QuizAnswerRepository quizAnswerRepository;

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
        QuizOption quizOption = quizOptionRepository.save(QuizOption.builder()
        .sequence(quizOptionDto.getOrder())
        .text(quizOptionDto.getText())
        .quiz(quizOptionDto.getQuiz())
        .build());
       return quizOption;
    }

    @Transactional(readOnly = false)
    public QuizAnswer createQuizAnswer(QuizAnswerDto quizAnswerDto){
        QuizAnswer quizAnswer = quizAnswerRepository.save(QuizAnswer.builder()
        .quiz(quizAnswerDto.getQuiz())
        .quizOption(quizAnswerDto.getQuizOption())
        .build());
        return quizAnswer;
    }

    public List<Quiz> findByUserId(Long userId){
        return quizRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Quiz> findByQuizGroupId(Long quizGroupId){
        return quizRepository.findByQuizGroupIdOrderByCreatedAtDesc(quizGroupId);
    }

    public List<QuizOption> findQuizOptionsByQuizId(Long quizId) {
        return quizOptionRepository.findByQuizIdOrderBySequenceAsc(quizId);
    }

    public QuizAnswer findQuizAnswerByQuizId(Long quizId) {
        return quizAnswerRepository.findByQuizId(quizId);
    }
}
