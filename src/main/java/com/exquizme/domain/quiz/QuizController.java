package com.exquizme.domain.quiz;

import com.exquizme.domain.user.User;
import com.exquizme.domain.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by godong9 on 2017. 7. 22..
 */

@Slf4j
@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;


    // 퀴즈 리스트 가져오는 API
    @GetMapping("/quizzes")
    public User getQuizzes(Principal principal) {
        return userService.getCurrentUser(principal);
    }

    // 퀴즈 그룹 만드는 API

    // 퀴즈 그룹 내려주는 API (퀴즈목록, 정답)

    // 개별 퀴즈 만드는 API (퀴즈 옵션들 포함)

    // 퀴즈 삭제

    // 퀴즈 그룹 가져오는 API (유저 ID)

    // 퀴즈 결과 생성 API

    // 퀴즈 결과 조회 API

}
