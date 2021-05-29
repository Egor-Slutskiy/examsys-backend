package com.examsys.controllers;

import com.examsys.entity.Exam;
import com.examsys.entity.Response;
import com.examsys.errors.ExamNotFoundException;
import com.examsys.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamRepository repository;

    public ExamController(ExamRepository repository){this.repository = repository;}

    @GetMapping
    public List<Exam> findAll(){

        /*
        List<Exam> exams = repository.findAll();
        Response response = new Response();
        response.message = exams.size() + ";";
        for (int i = 1; i <= exams.size(); i++){
            response.message += exams.get(i-1).toString() + ";";
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        */
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Exam findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ExamNotFoundException(id));
    }

    @PostMapping
    public Exam createExam(@RequestBody Exam exam) { return repository.save(exam); }

    @PutMapping("/{id}")
    Exam replaceExam(@RequestBody Exam newExam, @PathVariable Long id){
        return repository.findById(id)
                .map(exam -> {
                    exam.setName(newExam.getName());
                    exam.setCreate_time(newExam.getCreate_time());
                    exam.setClose_time(newExam.getClose_time());
                    return repository.save(exam);
                })
                .orElseGet(() -> repository.save(newExam));
    }

    @DeleteMapping("/{id}")
    void deleteExam(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
