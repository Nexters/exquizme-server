package com.exquizme.domain.quiz;

import lombok.Data;

/**
 * Created by garinkim on 2017. 8. 5..
 */
@Data
public class QuizForm {
    private String text;
    private QuizType type;
    private String[] options;
    private int answerIdx;
}
