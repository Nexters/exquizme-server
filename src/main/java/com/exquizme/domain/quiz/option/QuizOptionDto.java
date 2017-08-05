package com.exquizme.domain.quiz.option;

import com.exquizme.domain.quiz.Quiz;
import lombok.Data;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizOptionDto {
    private Integer order;
    private String text;
    private Quiz quiz;
}
