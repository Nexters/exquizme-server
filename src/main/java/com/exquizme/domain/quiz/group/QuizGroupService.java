package com.exquizme.domain.quiz.group;

import com.exquizme.domain.quiz.Quiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Slf4j
@Transactional(readOnly = true)
@Service
public class QuizGroupService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QuizGroupRepository quizGroupRepository;

    public QuizGroup findOne(Long id) {
        return quizGroupRepository.findOne(id);
    }

    @Transactional(readOnly = false)
    public QuizGroup createQuizGroup(QuizGroupDto quizGroupDto) {
        QuizGroup quizGroup = quizGroupRepository.save(QuizGroup.builder()
                .title(quizGroupDto.getTitle())
                .user(quizGroupDto.getUser())
                .build());

        String sql = "UPDATE quizzes SET quiz_group_id = " + quizGroup.getId() + " WHERE id IN (:ids)";

        Map<String, List> paramMap = Collections.singletonMap("ids", Arrays.asList(quizGroupDto.getQuizIds()));
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        template.update(sql, paramMap);

        return quizGroup;
    }
}
