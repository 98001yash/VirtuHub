package com.company.VirtuHub.Content_Management_service.service;


import com.company.VirtuHub.Content_Management_service.dtos.ContentApprovalRequestDto;
import com.company.VirtuHub.Content_Management_service.dtos.EventContentRequestDto;
import com.company.VirtuHub.Content_Management_service.dtos.EventContentResponseDto;
import com.company.VirtuHub.Content_Management_service.entities.EventContent;
import com.company.VirtuHub.Content_Management_service.enums.ContentStatus;
import com.company.VirtuHub.Content_Management_service.enums.ContentType;
import com.company.VirtuHub.Content_Management_service.exceptions.ResourceNotFoundException;
import com.company.VirtuHub.Content_Management_service.repository.EventContentRepository;
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
public class EventContentService {

    private final EventContentRepository repository;
    private final ModelMapper modelMapper;

    public EventContentResponseDto uploadContent(EventContentRequestDto requestDto) {
        log.info("Uploading content for eventId: {}", requestDto.getEventId());

        // Map request DTO to Entity without ID
        EventContent content = modelMapper.map(requestDto, EventContent.class);

        // Ensure ID is null to avoid update conflict
        content.setId(null);

        // Set default values
        content.setUploadedAt(LocalDateTime.now());
        content.setContentType(ContentType.valueOf(requestDto.getContentType()));

        // Save to DB
        EventContent savedContent = repository.save(content);

        // Convert to response DTO
        return modelMapper.map(savedContent, EventContentResponseDto.class);
    }


    public List<EventContentResponseDto> getContentByEventId(Long eventId){
        log.info("Fetching content for event ID: {}",eventId);

        List<EventContent> contentList = repository.findByEventId(eventId);

        return contentList.stream()
                .map(content->modelMapper.map(content, EventContentResponseDto.class))
                .collect(Collectors.toList());
    }


    public EventContentResponseDto getContentById(Long contentId){
        log.info("Fetching content for ID: {}",contentId);
        EventContent content = repository.findById(contentId)
                .orElseThrow(()->new ResourceNotFoundException("Content not found with ID: "+contentId));

        return modelMapper.map(content, EventContentResponseDto.class);
    }

    public EventContentResponseDto approveOrRejectContent(ContentApprovalRequestDto approvalDto) {
        EventContent content = repository.findById(approvalDto.getContentId())
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));

        if (approvalDto.isApproved()) {
            content.setStatus(ContentStatus.APPROVED);
            content.setRejectionReason(null);
        } else {
            content.setStatus(ContentStatus.REJECTED);
            content.setRejectionReason(approvalDto.getRejectionReason());
        }

        EventContent updated = repository.save(content);
        return modelMapper.map(updated, EventContentResponseDto.class);
    }

}
