package com.exquizme.domain.quiz.result;

import lombok.Data;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizResultData {
    private Long id;
    private Integer correct;
    private Integer wrong;
    private Integer time;
    private String nickname;
    private Integer score;
}
