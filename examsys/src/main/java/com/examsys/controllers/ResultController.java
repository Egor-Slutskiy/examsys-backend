package com.examsys.controllers;

import com.examsys.entity.Exam;
import com.examsys.entity.Response;
import com.examsys.entity.Result;
import com.examsys.entity.User;
import com.examsys.errors.ResultNotFoundException;
import com.examsys.repository.ExamRepository;
import com.examsys.repository.ResultRepository;
import com.examsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultRepository repository;
    private ExamRepository examRepository;
    private UserRepository userRepository;

    public ResultController(ResultRepository repository,  ExamRepository examRepository, UserRepository userRepository) {
        this.repository = repository;
        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Result> findAll() {return repository.findAll();}

    @GetMapping("/{id}")
    Result findOne(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResultNotFoundException(id)); }

    @GetMapping("/user/{id}")
    public List<Result> findByUser(@PathVariable Long id) {
        return repository.findAllByUser_id(id);
    }

    @GetMapping("/exam/{id}")
    public List<Result> findByExam(@PathVariable Long id) {
        return repository.findAllByExam_id(id);
    }

    @PostMapping
    public Result createResult(@RequestBody Result result) {
        Exam exam = examRepository.findExamById(result.getExam_id());
        User user = userRepository.findUserById(result.getUser_id());
        result.setExam(exam);
        result.setUser(user);
        return repository.save(result); }

    @PutMapping("/{id}")
    Result replaceResult(@RequestBody Result newResult, @PathVariable Long id) {
        return repository.findById(id)
                .map(result -> {
                    result.setId(id);
                    result.setResult(newResult.getResult());
                    result.setExam_id(newResult.getExam_id());
                    result.setExam(examRepository.findExamById(newResult.getExam_id()));
                    result.setUser_id(newResult.getUser_id());
                    result.setUser(userRepository.findUserById(newResult.getUser_id()));
                    return repository.save(result);
                }).orElseGet(() -> repository.save(newResult));
    }

    @DeleteMapping("/{id}")
    void deleteResult(@PathVariable Long id) { repository.deleteById(id); }
}
