package com.company.VirtuHub.Streaming_service.client;


import com.company.VirtuHub.Streaming_service.dtos.VirtualEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EventManagementService", url = "http://localhost:8080/events")
public interface  EventClient {

    @GetMapping("/{id}")
    VirtualEventDto getEventById(@PathVariable Long id);
}
