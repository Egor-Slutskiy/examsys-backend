package com.examsys.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "RESULT")
@NoArgsConstructor
@Data
public class Result {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    @Column(insertable = false, updatable = false)
    private Long exam_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(insertable = false, updatable = false)
    private Long user_id;

    private String result;

    public Result(Long id, Exam exam, Long exam_id, User user, Long user_id, String result) {
        this.id = id;
        this.exam = exam;
        this.exam_id = exam_id;
        this.user = user;
        this.user_id = user_id;
        this.result = result;
    }

    public Result(Long exam_id, Long user_id, String result) {
        this.exam_id = exam_id;
        this.user_id = user_id;
        this.result = result;
    }
}
