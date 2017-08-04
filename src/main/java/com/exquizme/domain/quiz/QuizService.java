package com.exquizme.domain.quiz;

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

}
