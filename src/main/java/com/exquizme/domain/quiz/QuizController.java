package com.exquizme.domain.quiz;

import com.exquizme.domain.quiz.group.QuizGroupDto;
import com.exquizme.domain.quiz.group.QuizGroupForm;
import com.exquizme.domain.quiz.group.QuizGroupService;
import com.exquizme.domain.user.User;
import com.exquizme.domain.user.UserService;
import com.exquizme.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    private QuizGroupService quizGroupService;

    @Autowired
    private UserService userService;


    // 퀴즈 리스트 가져오는 API
    @GetMapping("/quizzes")
    public User getQuizzes(Principal principal) {
        return userService.getCurrentUser(principal);
    }

    // 퀴즈 그룹 만드는 API

    /**
     * @api {post} /quiz/groups Create quiz group
     * @apiName CreateQuizGroup
     * @apiGroup QuizGroup
     *
     * @apiParam {String} title 퀴즈 그룹 제목
     * @apiParam {Number[]} quiz_ids 퀴즈 아이디 배열
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data QuizGroup 객체
     * @apiSuccess {Number} data.id QuizGroup id
     * @apiSuccess {Number} data.url QuizGroup url
     * @apiSuccess {Number} data.title QuizGroup title
     * @apiSuccess {Number} data.user_id QuizGroup 유저 id
     */
    @PostMapping("/quiz/groups")
    public ServerResponse postQuizGroup(Principal principal, @RequestBody @Valid QuizGroupForm quizGroupForm) {
//        User user = userService.getCurrentUser(principal); // TODO: 주석 해제
        User user = userService.getTestUser();

        QuizGroupDto quizGroupDto = new QuizGroupDto();
        quizGroupDto.setTitle(quizGroupForm.getTitle());
        quizGroupDto.setQuizIds(quizGroupForm.getQuizIds());
        quizGroupDto.setUser(user);

        quizGroupService.createQuizGroup(quizGroupDto);

        return ServerResponse.success();
    }

    // 퀴즈 그룹 내려주는 API (퀴즈목록, 정답)

    // 개별 퀴즈 만드는 API (퀴즈 옵션들 포함)

    // 퀴즈 삭제

    // 퀴즈 그룹 가져오는 API (유저 ID)

    // 퀴즈 결과 생성 API

    // 퀴즈 결과 조회 API

}
