package com.exquizme.domain.quiz;
import lombok.Data;
/**
 * Created by garinkim on 2017. 8. 4..
 */
@Data
public class QuizDto {
    private Long id;
    private String text;
    private String type;
    private Long user_id;
}
