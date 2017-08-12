package com.exquizme.domain.quiz.answer;

import lombok.Data;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizAnswerData {
    private Long id;
    private Long quizOptionId;

    public static QuizAnswerData getQuizAnswerData(QuizAnswer quizAnswer) {
        QuizAnswerData quizAnswerData = new QuizAnswerData();
        quizAnswerData.setId(quizAnswer.getId());
        quizAnswerData.setQuizOptionId(quizAnswer.getQuizOption().getId());
        return quizAnswerData;
    }
}
