package com.examsys.errors;

public class ExamNotFoundException extends RuntimeException{
    public ExamNotFoundException(Long id) { super("Could not find exam: " + id); }
}
