package com.exquizme.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends RuntimeException {
    private String message = "유저 정보 처리 중 에러가 발생하였습니다.";
}