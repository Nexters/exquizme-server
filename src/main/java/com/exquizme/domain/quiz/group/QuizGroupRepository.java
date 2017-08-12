package com.exquizme.domain.quiz.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Repository
public interface QuizGroupRepository extends JpaRepository<QuizGroup, Long> {
    List<QuizGroup> findByUserIdOrderByCreatedAtDesc(Long userId);
}
