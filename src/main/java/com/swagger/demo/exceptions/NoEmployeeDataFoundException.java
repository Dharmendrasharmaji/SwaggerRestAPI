package com.swagger.demo.exceptions;

public class NoEmployeeDataFoundException extends RuntimeException{
    public NoEmployeeDataFoundException(String message) {
        super(message);
    }
}
