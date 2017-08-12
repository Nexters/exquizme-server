package com.exquizme.domain.quiz.result;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    public static QuizResultData getQuizResultData(QuizResult quizResult) {
        QuizResultData quizResultData = new QuizResultData();
        quizResultData.setId(quizResult.getId());
        quizResultData.setCorrect(quizResult.getCorrect());
        quizResultData.setWrong(quizResult.getWrong());
        quizResultData.setTime(quizResult.getTime());
        quizResultData.setNickname(quizResult.getNickname());
        quizResultData.setScore(quizResult.getScore());

        return quizResultData;
    }

    public static List<QuizResultData> getQuizResultDataList(List<QuizResult> quizResultList) {
        return quizResultList.stream()
                .map(QuizResultData::getQuizResultData)
                .collect(Collectors.toList());
    }
}
