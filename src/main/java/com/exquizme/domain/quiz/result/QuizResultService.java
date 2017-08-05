package com.exquizme.domain.quiz.result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Slf4j
@Transactional(readOnly = true)
@Service
public class QuizResultService {
    @Autowired
    private QuizResultRepository quizResultRepository;

    public QuizResult findOne(Long id) {
        return quizResultRepository.findOne(id);
    }

    @Transactional(readOnly = false)
    public QuizResult create(QuizResultDto quizResultDto) {
        return quizResultRepository.save(QuizResult.builder()
                .correct(quizResultDto.getCorrect())
                .wrong(quizResultDto.getWrong())
                .time(quizResultDto.getTime())
                .nickname(quizResultDto.getNickname())
                .score(quizResultDto.getScore())
                .quizGroup(quizResultDto.getQuizGroup())
                .build());
    }
}
