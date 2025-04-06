package com.company.VirtuHub.EventManagementService.clients;


import com.company.VirtuHub.EventManagementService.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "User-service")
public interface  UserClient {

    @GetMapping("/auth/user/id/{userId}")
    UserDto getUserById(@PathVariable("userId") Long userId);
}
