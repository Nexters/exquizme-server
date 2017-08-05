package com.exquizme.domain.quiz;
import com.exquizme.domain.user.User;
import lombok.Data;
/**
 * Created by garinkim on 2017. 8. 4..
 */
@Data
public class QuizDto {
    private Long id;
    private String text;
    private QuizType type;
    private User userId;

}
