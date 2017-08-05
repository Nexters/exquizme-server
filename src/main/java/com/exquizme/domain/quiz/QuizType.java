package com.exquizme.domain.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by godong9 on 2017. 8. 5..
 */
@Getter
@AllArgsConstructor
public enum QuizType {
    SELECT("객관식"),
    WRITE("주관식");

    private String description;
}
