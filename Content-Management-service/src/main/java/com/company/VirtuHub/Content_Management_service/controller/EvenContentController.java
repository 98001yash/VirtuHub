package com.company.VirtuHub.Content_Management_service.controller;


import com.company.VirtuHub.Content_Management_service.dtos.EventContentRequestDto;
import com.company.VirtuHub.Content_Management_service.dtos.EventContentResponseDto;
import com.company.VirtuHub.Content_Management_service.service.EventContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
@Slf4j
public class EvenContentController {

    private final EventContentService contentService;

    // upload-content
    @PostMapping("/upload")
    public ResponseEntity<EventContentResponseDto> uploadContent(
            @RequestBody EventContentRequestDto requestDto
            ){
        log.info("Received request to upload event content: {}",requestDto);
        EventContentResponseDto responseDto = contentService.uploadContent(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // get all the content by the event ID
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventContentResponseDto>> getContentByEventId(
            @PathVariable Long eventId
    ) {
        log.info("Fetching content list for event ID: {}", eventId);
        List<EventContentResponseDto> responseList = contentService.getContentByEventId(eventId);
        return ResponseEntity.ok(responseList);
    }

    // get single  content by ID
    @GetMapping("/{contentId}")
    public ResponseEntity<EventContentResponseDto> getContentById(
            @PathVariable Long contentId
    ) {
        log.info("Fetching single content by ID: {}", contentId);
        EventContentResponseDto responseDto = contentService.getContentById(contentId);
        return ResponseEntity.ok(responseDto);
    }
}
