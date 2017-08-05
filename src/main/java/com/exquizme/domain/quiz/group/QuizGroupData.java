package com.exquizme.domain.quiz.group;

import com.exquizme.domain.quiz.QuizData;
import com.exquizme.domain.quiz.answer.QuizAnswerData;
import lombok.Data;

import java.util.List;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizGroupData {
    private Long id;
    private List<QuizData> quizList;
    private String url;
    private String title;
    private String userName;
    private QuizAnswerData quizAnswer;
}
