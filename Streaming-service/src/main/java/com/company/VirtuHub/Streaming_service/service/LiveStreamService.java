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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LiveStreamService {

    private final LiveStreamSessionRepository streamRepository;
    private final ModelMapper modelMapper;


    public LiveStreamSessionResponseDto createSession(LiveStreamSessionRequestDto requestDto) {
        log.info("Creating streaming session for event: {}", requestDto.getEventId());

        LiveStreamSession session = modelMapper.map(requestDto, LiveStreamSession.class);
        session.setStatus(StreamStatus.valueOf("SCHEDULED"));
        LiveStreamSession saved = streamRepository.save(session);

        return modelMapper.map(saved, LiveStreamSessionResponseDto.class);
    }

    public LiveStreamSessionResponseDto getSessionById(Long id) {
        log.info("Fetching session with ID: {}", id);
        LiveStreamSession session = streamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        return modelMapper.map(session, LiveStreamSessionResponseDto.class);
    }

    public List<LiveStreamSessionResponseDto> getAllSessions(){
        log.info("Fetching all streaming sessions");
        return streamRepository.findAll().stream()
                .map(session->modelMapper.map(session, LiveStreamSessionResponseDto.class))
                .collect(Collectors.toList());
    }

    public LiveStreamSessionResponseDto updateSession(Long id, LiveStreamSessionRequestDto requestDto) {
        log.info("Updating session with ID: {}", id);
        LiveStreamSession session = streamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + id));

        session.setStreamUrl(requestDto.getStreamUrl());
        session.setEmbedCode(requestDto.getEmbedCode());
        session.setIsRecordingEnabled(requestDto.getIsRecordingEnabled());
        session.setScheduledStartTime(LocalDateTime.parse(requestDto.getScheduledStartTime()));

        LiveStreamSession updated = streamRepository.save(session);
        return modelMapper.map(updated, LiveStreamSessionResponseDto.class);
    }

}
