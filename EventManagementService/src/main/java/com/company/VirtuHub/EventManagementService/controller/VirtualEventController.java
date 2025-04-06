package com.company.VirtuHub.EventManagementService.controller;

import com.company.VirtuHub.EventManagementService.advices.ApiResponse;
import com.company.VirtuHub.EventManagementService.dtos.VirtualEventDto;
import com.company.VirtuHub.EventManagementService.service.VirtualEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class VirtualEventController {

    private final VirtualEventService virtualEventService;

    @PostMapping
    public ResponseEntity<VirtualEventDto> createEvent(@RequestBody VirtualEventDto eventDto){
        log.info("Received request tpo create event: {}",eventDto.getEventName());
        VirtualEventDto createdEvent = virtualEventService.createEvent(eventDto);
        return ResponseEntity.ok(createdEvent);
    }


    @GetMapping
    public ResponseEntity<List<VirtualEventDto>> getAllEvents() {
        log.info("Received request to fetch all events");
        List<VirtualEventDto> events = virtualEventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VirtualEventDto> getEventById(@PathVariable Long id) {
        log.info("Received request to fetch event with id: {}", id);
        VirtualEventDto event = virtualEventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VirtualEventDto> updateEvent(@PathVariable Long id, @RequestBody VirtualEventDto eventDTO) {
        log.info("Received request to update event with id: {}", id);
        VirtualEventDto updatedEvent = virtualEventService.updateEvent(id, eventDTO);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.info("Received request to delete event with id: {}", id);
        virtualEventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<VirtualEventDto>>> searchEvents(
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) String platform,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventDateTime
    ) {
        List<VirtualEventDto> results = virtualEventService.searchEvents(eventName, platform, eventDateTime);
        return ResponseEntity.ok(ApiResponse.<List<VirtualEventDto>>builder()
                .timeStamp(LocalDateTime.now())
                .data(results)
                .build());
    }
}
