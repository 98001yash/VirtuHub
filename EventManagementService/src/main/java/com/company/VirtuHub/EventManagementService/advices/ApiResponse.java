package com.company.VirtuHub.EventManagementService.advices;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;


    public ApiResponse(){
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data){
        this();
        this.data  =  data;
    }

    public ApiResponse(ApiError error){
        this();
        this.error = error;
    }
}
