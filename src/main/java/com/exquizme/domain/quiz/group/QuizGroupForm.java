package com.exquizme.domain.quiz.group;

import lombok.Data;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizGroupForm {
    private String title;
    private Long[] quizIds;
}
