package com.examsys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "EXAM")
@NoArgsConstructor
@Data
public class Exam {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Timestamp create_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Timestamp close_time;

    public Exam(Long id, String name, String create_time, String close_time) {
        this.id = id;
        this.name = name;
        this.create_time = Timestamp.valueOf(create_time);
        this.close_time = Timestamp.valueOf(close_time);
    }
    public Exam(String name, String create_time, String close_time) {
        this.name = name;
        this.create_time = Timestamp.valueOf(create_time);
        this.close_time = Timestamp.valueOf(close_time);
    }
}
