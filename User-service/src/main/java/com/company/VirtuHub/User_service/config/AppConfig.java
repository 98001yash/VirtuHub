package com.company.VirtuHub.User_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
