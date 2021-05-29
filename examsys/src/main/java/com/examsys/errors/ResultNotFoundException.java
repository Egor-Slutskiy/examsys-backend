package com.examsys.errors;

public class ResultNotFoundException extends RuntimeException{
    public ResultNotFoundException(Long id) { super("Could not find result: " + id);}
}
