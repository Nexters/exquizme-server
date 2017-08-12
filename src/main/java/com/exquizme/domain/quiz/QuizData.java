package com.exquizme.domain.quiz;

import com.exquizme.domain.quiz.option.QuizOption;
import com.exquizme.domain.quiz.option.QuizOptionData;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizData {
    private Long id;
    private List<QuizOptionData> quizOptionList;

    public static QuizData getQuizData(Quiz quiz){
        QuizData quizData = new QuizData();
        quizData.setId(quiz.getId());
        return quizData;
    }

    // 퀴즈 리스트 가져오기
    public static List<QuizData> getQuizDataList(List<Quiz> quizList){
        return quizList.stream()
                .map(QuizData::getQuizData)
                .collect(Collectors.toList());
    }
}
