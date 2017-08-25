package com.exquizme.domain.quiz;

import com.exquizme.domain.quiz.answer.QuizAnswerData;
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
    private String text;
    private Integer quizType;
    private List<QuizOptionData> quizOptionList;
    private QuizAnswerData quizAnswer;

    public static QuizData getSimpleQuizData(Quiz quiz){
        QuizData quizData = new QuizData();
        quizData.setId(quiz.getId());
        quizData.setText(quiz.getText());
        quizData.setQuizType((quiz.getType().toString().equals("SELECT") ? 0 : 1));
        return quizData;
    }

    public static QuizData getQuizData(Quiz quiz){
        QuizData quizData = new QuizData();
        quizData.setId(quiz.getId());
        quizData.setText(quiz.getText());
        quizData.setQuizType((quiz.getType().toString().equals("SELECT") ? 0 : 1));
        quizData.setQuizOptionList(null);
        quizData.setQuizAnswer(null);
        return quizData;
    }

    // 퀴즈 리스트 가져오기
    public static List<QuizData> getSimpleQuizDataList(List<Quiz> quizList){
        return quizList.stream()
                .map(QuizData::getSimpleQuizData)
                .collect(Collectors.toList());
    }

    public static List<QuizData> getQuizDataList(List<Quiz> quizList) {
        return quizList.stream()
                .map(QuizData::getQuizData)
                .collect(Collectors.toList());
    }
}
