package com.evilapp.fire.exception;

public class FireException extends RuntimeException {
    private String message;
    
    public FireException(String message) {
        super(message);
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
