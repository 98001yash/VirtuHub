package com.company.VirtuHub.Content_Management_service.exceptions;

public class RuntimeConflictException extends RuntimeException{
    public RuntimeConflictException() {
    }

    public RuntimeConflictException(String message) {
        super(message);
    }
}

