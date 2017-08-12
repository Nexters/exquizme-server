package com.exquizme.domain.quiz.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by godong9 on 2017. 8. 5..
 */
@Repository
public interface QuizOptionRepository extends JpaRepository<QuizOption, Long> {
    List<QuizOption> findByQuizIdOrderBySequenceAsc(Long quizId);
}