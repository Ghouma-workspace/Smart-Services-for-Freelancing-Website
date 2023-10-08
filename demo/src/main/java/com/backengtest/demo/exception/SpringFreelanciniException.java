package com.backengtest.demo.exception;

public class SpringFreelanciniException extends RuntimeException{
    public SpringFreelanciniException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringFreelanciniException(String exMessage) {
        super(exMessage);
    }
}
