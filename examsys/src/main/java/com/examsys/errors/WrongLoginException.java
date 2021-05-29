package com.examsys.errors;

public class WrongLoginException extends RuntimeException{
    public WrongLoginException() {super("Wrong login");}
}
