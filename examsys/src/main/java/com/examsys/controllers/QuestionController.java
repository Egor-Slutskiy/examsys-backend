package com.examsys.controllers;

import com.examsys.entity.Exam;
import com.examsys.entity.Question;
import com.examsys.entity.Response;
import com.examsys.errors.QuestionNotFoundException;
import com.examsys.repository.ExamRepository;
import com.examsys.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionRepository repository;
    private ExamRepository examRepository;

    public QuestionController(QuestionRepository repository, ExamRepository examRepository) { this.repository = repository;
    this.examRepository = examRepository;}

    @GetMapping
    public List<Question> findAll() { return repository.findAll(); }

    @GetMapping("/find_by_exam/{id}")
    public List<Question> findByExam(@PathVariable Long id) {
        /*
        List<Question> questions = repository.findAllByExam_id(id);
        Response response = new Response();
        response.message = questions.size() + ";";
        for (int i = 1; i <= questions.size(); i++){
            response.message += questions.get(i-1).toString() + ";";
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        */
        return repository.findAllByExam_id(id);
    }


    @GetMapping("/{id}")
    Question findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @PostMapping
    public Question questionCreate(@RequestBody Question question) {
        Exam exam = examRepository.findExamById(question.getExam_id());
        question.setExam(exam);
        return repository.save(question); }

    @PutMapping("/{id}")
    Question replaceQuestion(@RequestBody Question newQuestion, @PathVariable Long id){
        return repository.findById(id)
                .map(question -> {
                    question.setAnswers(newQuestion.getAnswers());
                    question.setBody(newQuestion.getBody());
                    question.setCorrect_answer(newQuestion.getCorrect_answer());
                    Exam exam = examRepository.findExamById(newQuestion.getExam_id());
                    question.setExam(exam);
                    question.setExam_id(newQuestion.getExam_id());
                    return repository.save(question);
                })
                .orElseGet(() -> repository.save(newQuestion));
    }

    @DeleteMapping("/{id}")
    void deleteQuestion(@PathVariable Long id) { repository.deleteById(id); }
}
