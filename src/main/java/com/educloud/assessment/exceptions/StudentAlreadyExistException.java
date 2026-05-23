package com.educloud.assessment.exceptions;

public class StudentAlreadyExistException extends RuntimeException{
    public StudentAlreadyExistException(String message){
        super(message);
    }
}
