package com.company.VirtuHub.Streaming_service.controller;


import com.company.VirtuHub.Streaming_service.dtos.LiveStreamSessionRequestDto;
import com.company.VirtuHub.Streaming_service.dtos.LiveStreamSessionResponseDto;
import com.company.VirtuHub.Streaming_service.service.LiveStreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/streams")
@RequiredArgsConstructor
@Slf4j
public class StreamingController {

    private final LiveStreamService streamingService;

    @PostMapping
    public ResponseEntity<LiveStreamSessionResponseDto> createStream(@RequestBody LiveStreamSessionRequestDto requestDto) {
        log.info("Creating stream for event ID {}", requestDto.getEventId());
        return ResponseEntity.ok(streamingService.createStream(requestDto));
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<LiveStreamSessionResponseDto> startStream(@PathVariable Long id) {
        log.info("Starting stream with ID {}", id);
        return ResponseEntity.ok(streamingService.startStream(id));
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<LiveStreamSessionResponseDto> endStream(@PathVariable Long id) {
        log.info("Ending stream with ID {}", id);
        return ResponseEntity.ok(streamingService.endStream(id));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<LiveStreamSessionResponseDto>> getStreamsByEvent(@PathVariable Long eventId) {
        log.info("Fetching streams for event ID {}", eventId);
        return ResponseEntity.ok(streamingService.getAllStreamsByEvent(eventId));
    }
}

