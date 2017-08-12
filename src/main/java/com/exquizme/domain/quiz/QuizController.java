package com.exquizme.domain.quiz;

import com.exquizme.domain.quiz.answer.QuizAnswer;
import com.exquizme.domain.quiz.answer.QuizAnswerDto;
import com.exquizme.domain.quiz.group.QuizGroup;
import com.exquizme.domain.quiz.group.QuizGroupDto;
import com.exquizme.domain.quiz.group.QuizGroupForm;
import com.exquizme.domain.quiz.group.QuizGroupService;
import com.exquizme.domain.quiz.option.QuizOption;
import com.exquizme.domain.quiz.group.*;
import com.exquizme.domain.quiz.option.QuizOptionDto;
import com.exquizme.domain.quiz.result.*;
import com.exquizme.domain.user.User;
import com.exquizme.domain.user.UserService;
import com.exquizme.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
    private QuizResultService quizResultService;

    @Autowired
    private UserService userService;

    /**
     * @api {post} /api/quiz/groups Create quiz group
     * @apiName CreateQuizGroup
     * @apiGroup QuizGroup
     *
     * @apiParam {String} title 퀴즈 그룹 제목
     * @apiParam {L[]} quiz_ids 퀴즈 아이디 배열
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data QuizGroup 객체
     * @apiSuccess {Number} data.id QuizGroup id
     * @apiSuccess {Number} data.url QuizGroup url
     * @apiSuccess {Number} data.title QuizGroup title
     */
    @PostMapping("/quiz/groups")
    public ServerResponse postQuizGroup(Principal principal, @RequestBody @Valid QuizGroupForm quizGroupForm) {
//        User user = userService.getCurrentUser(principal); // TODO: 주석 해제
        User user = userService.getTestUser();

        QuizGroupDto quizGroupDto = new QuizGroupDto();
        quizGroupDto.setTitle(quizGroupForm.getTitle());
        quizGroupDto.setQuizIds(quizGroupForm.getQuizIds());
        quizGroupDto.setUser(user);

        QuizGroup quizGroup = quizGroupService.createQuizGroup(quizGroupDto);
        return ServerResponse.success(QuizGroupData.getSimpleQuizGroupData(quizGroup));
    }

    // 퀴즈 그룹 내려주는 API (퀴즈목록, 정답)
    @GetMapping("/quiz/groups/{id}")
    public ServerResponse getQuizGroup(@PathVariable @Valid Long id) {
        // TODO: 퀴즈목록, 정답 내려줘야 함!
        return ServerResponse.success();
    }

    /**
     * @api {get} /api/quiz/user/groups Get user quiz groups
     * @apiName GetUserQuizGroups
     * @apiGroup QuizGroup
     *
     * @apiDescription 현재 로그인되어 있는 유저의 퀴즈 목록 가져오는 API
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data QuizGroup 객체
     * @apiSuccess {Number} data.id QuizGroup id
     * @apiSuccess {Number} data.url QuizGroup url
     * @apiSuccess {Number} data.title QuizGroup title
     */
    @GetMapping("/quiz/user/groups")
    public ServerResponse getQuizGroupByUserId(Principal principal) {
        // TODO: 유저 ID 기반으로 퀴즈 그룹 가져와야함
        User user = userService.getCurrentUser(principal); // TODO: 주석 해제
//        User user = userService.getTestUser();

        List<QuizGroup> quizGroupList = quizGroupService.findByUserId(user.getId());
        return ServerResponse.success(QuizGroupData.getSimpleQuizGroupDataList(quizGroupList));
    }

    /**
     * @api {get} /api/quizzes  Get Quizzes
     * @apiName GetQuizzes
     * @apiGroup Quiz
     *
     * @apiDescription 퀴즈 리스트를 가져오는 API
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data Quiz 객체
     * @apiSuccess {Number} data.id Quiz id
     * @apiSuccess {String} data.quiz_option_list Quiz Option list
     */
    // 퀴즈 리스트 가져오는 API
    @GetMapping("/quizzes")
    public ServerResponse getQuizzes(Principal principal) {
        User user = userService.getCurrentUser(principal);
        //User user = userService.getTestUser();
        List<Quiz> quizList = quizService.findByUserId(user.getId());
        return ServerResponse.success(QuizData.getQuizDataList(quizList));
    }


    /**
     * @api {post} /api/quizzes Create quiz
     * @apiName CreateQuiz
     * @apiGroup Quiz
     *
     * @apiParam {Stinrg} text 퀴즈 내용
     * @apiParam {String} type 퀴즈 타입
     * @apiParam {String[]} options 퀴즈 옵션
     * @apiParam {Number} answerIdx 퀴즈 정답 인덱스
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data Quiz 객체
     * @apiSuccess {Number} data.id Quiz Id
     * @apiSuccess {String} data.text 퀴즈 내용
     * @apiSuccess {Number} data.quiz_group 퀴즈 그룹 아이디
     * @apiSuccess {Object} data.user 퀴즈 작성자
     */
    // 개별 퀴즈 만드는 API (퀴즈 옵션들 포함)
    @PostMapping("/quizzes")
    public ServerResponse postQuiz(Principal principal, @RequestBody @Valid QuizForm quizForm){

        // TODO: quizzes -> quiz_options -> quiz_answers
//        User user = userService.getCurrentUser(principal);
        User user = userService.getTestUser();

        // quizzes
        QuizDto quizDto = new QuizDto();
        quizDto.setText(quizForm.getText());
        quizDto.setType(quizForm.getType());
        quizDto.setUser(user);
        Quiz newQuiz = quizService.createQuiz(quizDto);

        QuizAnswerDto quizAnswerDto = new QuizAnswerDto();

        //quiz_option
        QuizOption quizOption = new QuizOption();
        String[] options = quizForm.getOptions();
        QuizOptionDto quizOptionDto = new QuizOptionDto();
        for(int i=0 ;i <options.length ;i++){
            quizOptionDto.setOrder(i);
            quizOptionDto.setQuiz(newQuiz);
            quizOptionDto.setText(options[i]);
            quizOption = quizService.createQuizOption(quizOptionDto);
            if(i == quizForm.getAnswerIdx()){
                quizAnswerDto.setQuizOption(quizOption);
            }
        }

        //quiz_Answer
        quizAnswerDto.setQuiz(newQuiz);
        QuizAnswer quizAnswer = quizService.createQuizAnswer(quizAnswerDto);
        return ServerResponse.success(newQuiz);
    }

    // 퀴즈 삭제
    /**
     * @api {delete} /api/quizzes/:id Delete Quizzes
     * @apiName deleteQuiz
     * @apiGroup Quiz
     *
     * @apiParam {Number} id 퀴즈 아이디
     *
     * @apiDescription 단일 퀴즈 삭제하는 API
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data null
     */
    @DeleteMapping("/quizzes/{id}")
    public ServerResponse deleteQuiz(@PathVariable @Valid Long id){
        quizService.deleteQuiz(id);
        return ServerResponse.success(null);
    }

    // 퀴즈 그룹 가져오는 API (유저 ID)
    /**
     * @api {post} /api/quiz/results Create quiz result
     * @apiName CreateQuizResult
     * @apiGroup QuizResult
     *
     * @apiParam {Number} quiz_group_id 퀴즈 그룹 id
     * @apiParam {Number} correct 맞춘 퀴즈 개수
     * @apiParam {Number} wrong 틀린 퀴즈 개수
     * @apiParam {Number} time 걸린 시간 (초)
     * @apiParam {String} nickname 닉네임

     * @apiParam {Number} quiz_group_id 퀴즈 그룹 id
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data QuizResult 객체
     * @apiSuccess {Number} data.id QuizResult id
     * @apiSuccess {Number} data.correct 정답 개수
     * @apiSuccess {Number} data.wrong 오답 개수
     * @apiSuccess {Number} data.time 걸린 시간
     * @apiSuccess {Number} data.nickname 닉네임
     */
    @PostMapping("/quiz/results")
    public ServerResponse postQuizResult(@RequestBody @Valid QuizResultForm quizResultForm) {
        QuizResultDto quizResultDto = new QuizResultDto();
        quizResultDto.setQuizGroup(quizGroupService.findOne(quizResultForm.getQuizGroupId()));
        quizResultDto.setCorrect(quizResultForm.getCorrect());
        quizResultDto.setWrong(quizResultForm.getWrong());
        quizResultDto.setTime(quizResultForm.getTime());
        quizResultDto.setNickname(quizResultForm.getNickname());

        QuizResult quizResult = quizResultService.create(quizResultDto);
        return ServerResponse.success(QuizResultData.getQuizResultData(quizResult));
    }

    /**
     * @api {get} /api/quiz/results/:guizGroupId Get quiz result lists
     * @apiName GetQuizResults
     * @apiGroup QuizResult
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object[]} data QuizResult 객체 배열
     * @apiSuccess {Number} data.id QuizResult id
     * @apiSuccess {Number} data.correct 정답 개수
     * @apiSuccess {Number} data.wrong 오답 개수
     * @apiSuccess {Number} data.time 걸린 시간
     * @apiSuccess {Number} data.nickname 닉네임
     */
    @GetMapping("/quiz/results/{quizGroupId}")
    public ServerResponse getQuizResults(@PathVariable @Valid Long quizGroupId) {
        List<QuizResult> quizResultList = quizResultService.findByQuizGroup(quizGroupId);
        return ServerResponse.success(QuizResultData.getQuizResultDataList(quizResultList));
    }
}
