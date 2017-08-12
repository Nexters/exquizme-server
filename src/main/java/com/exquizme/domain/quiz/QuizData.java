package com.exquizme.domain.quiz;

import com.exquizme.domain.quiz.option.QuizOptionData;
import lombok.Data;

import java.util.List;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
public class QuizData {
    private Long id;
    private List<QuizOptionData> quizOptionList;

//    public static QuizData getQuizData(Quiz quiz){
//
//
//    }
//    

}
