package com.company.VirtuHub.User_service.exceptions;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {

    private LocalDateTime timeStamp;
    private String error;
    private HttpStatus statusCode;

    public ApiError(){
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatus statusCode){
        this();
        this.error = error;
        this.statusCode = statusCode;
    }
}
