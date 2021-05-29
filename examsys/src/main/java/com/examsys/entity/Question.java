package com.examsys.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "QUESTION")
@NoArgsConstructor
@Data
public class Question {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY) Long question_id;
    String body;
    String answers;
    String correct_answer;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    @Column(insertable = false, updatable = false)
    private Long exam_id;

    public Question(Long question_id, String body, String answers, String correct_answer, Exam exam, Long exam_id){
        this.question_id = question_id;
        this.body = body;
        this.answers = answers;
        this.correct_answer = correct_answer;
        this.exam = exam;
        this.exam_id = exam_id;
    }

    public Question(Long question_id, String body, String answers, String correct_answer, Long exam_id){
        this.question_id = question_id;
        this.body = body;
        this.answers = answers;
        this.correct_answer = correct_answer;
        this.exam_id = exam_id;
    }
}
