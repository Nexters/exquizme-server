package com.exquizme.domain.quiz.result;

import com.exquizme.domain.quiz.group.QuizGroup;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by godong9 on 2017. 8. 5..
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Entity
@Table(name = "quiz_results")
@EntityListeners({AuditingEntityListener.class})
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_group_id")
    private QuizGroup quizGroup;

    @Column(name = "correct")
    private Integer correct;

    @Column(name = "wrong")
    private Integer wrong;

    @Column(name = "time")
    private Integer time;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "score", nullable = true)
    private Integer score;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
