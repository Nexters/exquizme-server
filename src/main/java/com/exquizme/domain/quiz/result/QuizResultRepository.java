package com.exquizme.domain.quiz.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by godong9 on 2017. 8. 5..
 */
@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByQuizGroupIdOrderByCorrectDescTimeAsc(Long quizGroupId);
}
