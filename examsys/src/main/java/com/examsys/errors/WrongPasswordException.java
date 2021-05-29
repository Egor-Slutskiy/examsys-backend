package com.examsys.errors;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(){super("wrong password");}
}
