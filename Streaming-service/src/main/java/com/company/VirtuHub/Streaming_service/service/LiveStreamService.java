package com.company.VirtuHub.Streaming_service.service;


import com.company.VirtuHub.Streaming_service.dtos.LiveStreamSessionRequestDto;
import com.company.VirtuHub.Streaming_service.dtos.LiveStreamSessionResponseDto;
import com.company.VirtuHub.Streaming_service.entities.LiveStreamSession;
import com.company.VirtuHub.Streaming_service.enums.StreamStatus;
import com.company.VirtuHub.Streaming_service.exceptions.ResourceNotFoundException;
import com.company.VirtuHub.Streaming_service.repository.LiveStreamSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LiveStreamService {

    private final LiveStreamSessionRepository streamRepository;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    public LiveStreamSessionResponseDto createStream(LiveStreamSessionRequestDto requestDto) {
        LiveStreamSession session = LiveStreamSession.builder()
                .eventId(requestDto.getEventId())
                .streamUrl(requestDto.getStreamUrl())
                .embedCode(requestDto.getEmbedCode())
                .isRecordingEnabled(requestDto.getIsRecordingEnabled())
                .status(StreamStatus.SCHEDULED)
                .scheduledStartTime(LocalDateTime.parse(requestDto.getScheduledStartTime(), formatter))
                .build();

        LiveStreamSession saved = streamRepository.save(session);
        log.info("Created new stream session for event ID {}", saved.getEventId());
        return convertToResponseDto(saved);
    }


    public LiveStreamSessionResponseDto startStream(Long streamId) {
        LiveStreamSession session = streamRepository.findById(streamId)
                .orElseThrow(() -> new ResourceNotFoundException("Stream session not found with ID: " + streamId));

        session.setStatus(StreamStatus.LIVE);
        session.setActualStartTime(LocalDateTime.now());

        LiveStreamSession updated = streamRepository.save(session);
        log.info("Started stream session with ID {}", streamId);

        //  TODO: Notify users that the live session has started (via Notification Service)
        return convertToResponseDto(updated);
    }

    public LiveStreamSessionResponseDto endStream(Long streamId) {
        LiveStreamSession session = streamRepository.findById(streamId)
                .orElseThrow(() -> new ResourceNotFoundException("Stream session not found with ID: " + streamId));

        session.setStatus(StreamStatus.ENDED);
        session.setEndTime(LocalDateTime.now());
        if (session.getIsRecordingEnabled() != null && session.getIsRecordingEnabled()) {
            session.setRecordingUrl("https://recordings.virtuhub.com/" + session.getId());
        }

        LiveStreamSession updated = streamRepository.save(session);
        log.info("Ended stream session with ID {}", streamId);
        return convertToResponseDto(updated);
    }


    public List<LiveStreamSessionResponseDto> getAllStreamsByEvent(Long eventId) {
        List<LiveStreamSession> sessions = streamRepository.findByEventId(eventId);
        log.info("Fetched {} stream sessions for event ID {}", sessions.size(), eventId);
        return sessions.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private LiveStreamSessionResponseDto convertToResponseDto(LiveStreamSession session) {
        return LiveStreamSessionResponseDto.builder()
                .id(session.getId())
                .eventId(session.getEventId())
                .streamUrl(session.getStreamUrl())
                .embedCode(session.getEmbedCode())
                .status(session.getStatus().toString())
                .isRecordingEnabled(session.getIsRecordingEnabled())
                .recordedVideoUrl(session.getRecordingUrl())
                .scheduledStartTime(session.getScheduledStartTime() != null ? session.getScheduledStartTime().toString() : null)
                .build();
    }
}