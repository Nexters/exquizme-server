package com.exquizme.domain.quiz.group;

import com.exquizme.domain.quiz.QuizData;
import com.exquizme.domain.quiz.answer.QuizAnswerData;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    public static QuizGroupData getSimpleQuizGroupData(QuizGroup quizGroup) {
        QuizGroupData quizGroupData = new QuizGroupData();
        quizGroupData.setId(quizGroup.getId());
        quizGroupData.setUrl("/" + quizGroup.getId());
        quizGroupData.setTitle(quizGroup.getTitle());
        quizGroupData.setUserName(quizGroup.getUser().getNickname());

        return quizGroupData;
    }

    public static List<QuizGroupData> getSimpleQuizGroupDataList(List<QuizGroup> quizGroupList) {
        return quizGroupList.stream()
                .map(QuizGroupData::getSimpleQuizGroupData)
                .collect(Collectors.toList());
    }
}
