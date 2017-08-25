package com.exquizme.domain.quiz;

import com.exquizme.domain.quiz.answer.QuizAnswerData;
import com.exquizme.domain.quiz.answer.QuizAnswerDto;
import com.exquizme.domain.quiz.group.*;
import com.exquizme.domain.quiz.option.QuizOption;
import com.exquizme.domain.quiz.option.QuizOptionData;
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
     * @apiParam {Number[]} quiz_ids 퀴즈 아이디 배열
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data QuizGroup 객체
     * @apiSuccess {Number} data.id QuizGroup id
     * @apiSuccess {String} data.url QuizGroup url
     * @apiSuccess {String} data.title QuizGroup title
     */
    @PostMapping("/quiz/groups")
    public ServerResponse postQuizGroup(Principal principal, @RequestBody @Valid QuizGroupForm quizGroupForm) {
        User user = userService.getCurrentUser(principal);
//        User user = userService.getTestUser();

        QuizGroupDto quizGroupDto = new QuizGroupDto();
        quizGroupDto.setTitle(quizGroupForm.getTitle());
        quizGroupDto.setQuizIds(quizGroupForm.getQuizIds());
        quizGroupDto.setUser(user);

        QuizGroup quizGroup = quizGroupService.createQuizGroup(quizGroupDto);
        return ServerResponse.success(QuizGroupData.getSimpleQuizGroupData(quizGroup));
    }

    /**
     * @api {get} /api/quiz/groups/:id Get quiz group
     * @apiName GetQuizGroup
     * @apiGroup QuizGroup
     *
     * @apiSuccess {Number} status 상태코드
     * @apiSuccess {Object} data QuizGroup 객체
     * @apiSuccess {Number} data.id QuizGroup id
     * @apiSuccess {String} data.url QuizGroup url
     * @apiSuccess {String} data.title QuizGroup title
     * @apiSuccess {String} data.user_name QuizGroup 퀴즈 생성한 유저 네임
     * @apiSuccess {Object[]} data.quiz_list Quiz 객체 배열
     * @apiSuccess {Number} data.quiz_list.id Quiz id
     * @apiSuccess {Object[]} data.quiz_list.quiz_option_list QuizOption 객체 배열
     * @apiSuccess {Number} data.quiz_list.quiz_option_list.id QuizOption id
     * @apiSuccess {Number} data.quiz_list.quiz_option_list.order QuizOption 순서
     * @apiSuccess {String} data.quiz_list.quiz_option_list.text QuizOption 텍스트
     * @apiSuccess {Object} data.quiz_list.quiz_answer QuizAnswer 객체
     * @apiSuccess {Object} data.quiz_list.quiz_answer.quiz_option_id 정답인 QuizOption의 id
     *
     * @apiSuccessExample {json} Success-Response:
     *      HTTP/1.1 200 OK
     *      {
     *          "status":200,
     *          "data":{
     *              "id":1,
     *              "quiz_list":[
     *                  {
     *                      "id":3,
     *                      "quiz_option_list":[
     *                          {"id":9,"order":0,"text":"test1"},
     *                          {"id":10,"order":1,"text":"test2"},
     *                          {"id":11,"order":2,"text":"test3"},
     *                          {"id":12,"order":3,"text":"test4"}
     *                      ],
     *                      "quiz_answer":{"id":3,"quiz_option_id":10}
     *                  },
     *                  {
     *                      "id":2,
     *                      "quiz_option_list":[
     *                          {"id":5,"order":0,"text":"test1"},
     *                          {"id":6,"order":1,"text":"test2"},
     *                          {"id":7,"order":2,"text":"test3"},
     *                          {"id":8,"order":3,"text":"test4"}
     *                      ],
     *                      "quiz_answer":{"id":2,"quiz_option_id":8}
     *                  },
     *                  {
     *                      "id":1,
     *                      "quiz_option_list":[
     *                          {"id":1,"order":0,"text":"test1"},
     *                          {"id":2,"order":1,"text":"test2"},
     *                          {"id":3,"order":2,"text":"test3"},
     *                          {"id":4,"order":3,"text":"test4"}
     *                      ],
     *                      "quiz_answer":{"id":1,"quiz_option_id":3}
     *                  }
     *              ],
     *              "url":"/1",
     *              "title":"테스트퀴즈그룹",
     *              "user_name":"Donghyun Go"
     *          },
     *          "message":null,
     *          "count":null,
     *          "error":null
     *      }
     */
    @GetMapping("/quiz/groups/{id}")
    public ServerResponse getQuizGroup(@PathVariable @Valid Long id) {
        QuizGroup quizGroup = quizGroupService.findOne(id);
        QuizGroupData quizGroupData = QuizGroupData.getQuizGroupData(quizGroup);

        List<Quiz> quizList = quizService.findByQuizGroupId(id);
        List<QuizData> quizDataList = QuizData.getQuizDataList(quizList);

        quizDataList.forEach(quizData -> {
                    List<QuizOption> quizOptionList = quizService.findQuizOptionsByQuizId(quizData.getId());
                    List<QuizOptionData> quizOptionDataList = QuizOptionData.getQuizOptionDataList(quizOptionList);
                    quizData.setQuizOptionList(quizOptionDataList);

                    QuizAnswerData quizAnswerData = QuizAnswerData.getQuizAnswerData(quizService.findQuizAnswerByQuizId(quizData.getId()));
                    quizData.setQuizAnswer(quizAnswerData);
                });

        quizGroupData.setQuizList(quizDataList);

        return ServerResponse.success(quizGroupData);
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
        User user = userService.getCurrentUser(principal);
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
        return ServerResponse.success(QuizData.getSimpleQuizDataList(quizList));
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
        User user = userService.getCurrentUser(principal);
//        User user = userService.getTestUser();

        // quizzes
        QuizDto quizDto = new QuizDto();
        quizDto.setText(quizForm.getText());
        quizDto.setType(quizForm.getType());
        quizDto.setUser(user);
        Quiz newQuiz = quizService.createQuiz(quizDto);

        QuizAnswerDto quizAnswerDto = new QuizAnswerDto();

        //quiz_option
        String[] options = quizForm.getOptions();
        QuizOptionDto quizOptionDto = new QuizOptionDto();
        for(int i=0 ;i <options.length ;i++){
            quizOptionDto.setOrder(i);
            quizOptionDto.setQuiz(newQuiz);
            quizOptionDto.setText(options[i]);
            QuizOption quizOption = quizService.createQuizOption(quizOptionDto);
            if(i == quizForm.getAnswerIdx()){
                quizAnswerDto.setQuizOption(quizOption);
            }
        }

        //quiz_Answer
        quizAnswerDto.setQuiz(newQuiz);
        quizService.createQuizAnswer(quizAnswerDto);

        return ServerResponse.success(QuizData.getSimpleQuizData(newQuiz));
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
     * @api {get} /api/quiz/results/:guizGroupId Get quiz result list
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
