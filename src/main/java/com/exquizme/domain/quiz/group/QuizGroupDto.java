package com.exquizme.domain.quiz.group;

import com.exquizme.domain.user.User;
import lombok.Data;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizGroupDto {
    private String title;
    private User user;
    private Long[] quizIds;
}
