package com.spring_magazine.common.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {

        super(message);
    }
}