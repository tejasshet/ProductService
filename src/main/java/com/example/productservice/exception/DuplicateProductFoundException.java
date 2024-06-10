package com.example.productservice.exception;

public class DuplicateProductFoundException extends RuntimeException {
    public DuplicateProductFoundException(String message){
        super(message);
    }


}
