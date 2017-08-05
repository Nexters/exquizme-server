package com.exquizme.domain.quiz.answer;

import com.exquizme.domain.quiz.Quiz;
import com.exquizme.domain.quiz.option.QuizOption;
import lombok.Data;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizAnswerDto {
    private Quiz quiz;
    private QuizOption quizOption;
}
