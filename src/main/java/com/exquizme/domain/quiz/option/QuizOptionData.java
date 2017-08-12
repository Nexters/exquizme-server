package com.exquizme.domain.quiz.option;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizOptionData {
    private Long id;
    private Integer order;
    private String text;

    public static QuizOptionData getQuizOptionData(QuizOption quizOption) {
        QuizOptionData quizOptionData = new QuizOptionData();
        quizOptionData.setId(quizOption.getId());
        quizOptionData.setOrder(quizOption.getSequence());
        quizOptionData.setText(quizOption.getText());
        return quizOptionData;
    }

    public static List<QuizOptionData> getQuizOptionDataList(List<QuizOption> quizOptionList) {
        return quizOptionList.stream()
                .map(QuizOptionData::getQuizOptionData)
                .collect(Collectors.toList());
    }
}
