package com.company.VirtuHub.EventManagementService.service;

import com.company.VirtuHub.EventManagementService.clients.UserClient;
import com.company.VirtuHub.EventManagementService.dtos.UserDto;
import com.company.VirtuHub.EventManagementService.dtos.VirtualEventDto;
import com.company.VirtuHub.EventManagementService.entities.VirtualEvent;
import com.company.VirtuHub.EventManagementService.exceptions.ResourceNotFoundException;
import com.company.VirtuHub.EventManagementService.repository.VirtualEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VirtualEventService {

    private final VirtualEventRepository virtualEventRepository;
    private final ModelMapper modelMapper;
    private final UserClient userClient;

    public VirtualEventDto createEvent(VirtualEventDto eventDto) {
        log.info("Creating a new Virtual event: {}", eventDto.getEventName());

        if (eventDto.getHostUserId() == null) {
            throw new IllegalArgumentException("Host User ID is required to create an event.");
        }

        // Validate user is a ADMIN
        UserDto userDto = userClient.getUserById(eventDto.getHostUserId());
        if (!"ADMIN".equalsIgnoreCase(String.valueOf(userDto.getRole()))) {
            throw new RuntimeException("Only users with role ADMIN can create events.");
        }

        VirtualEvent event = modelMapper.map(eventDto, VirtualEvent.class);
        VirtualEvent savedEvent = virtualEventRepository.save(event);
        return modelMapper.map(savedEvent, VirtualEventDto.class);
    }


    public List<VirtualEventDto> getAllEvents() {
        log.info("Fetching all virtual events");
        return virtualEventRepository.findAll().stream()
                .map(event -> modelMapper.map(event, VirtualEventDto.class))
                .collect(Collectors.toList());
    }

    public VirtualEventDto getEventById(Long id) {
        log.info("Fetching virtual event with id: {}", id);
        VirtualEvent event = virtualEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return modelMapper.map(event, VirtualEventDto.class);
    }

    public VirtualEventDto updateEvent(Long id, VirtualEventDto eventDetails) {
        log.info("Updating virtual event with id: {}", id);
        VirtualEvent existingEvent = virtualEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        existingEvent.setEventName(eventDetails.getEventName());
        existingEvent.setEventDateTime(eventDetails.getEventDateTime());
        existingEvent.setPlatform(eventDetails.getPlatform());
        existingEvent.setAccessLink(eventDetails.getAccessLink());
        existingEvent.setEventDescription(eventDetails.getEventDescription());
        existingEvent.setHostUserId(eventDetails.getHostUserId());

        VirtualEvent updatedEvent = virtualEventRepository.save(existingEvent);
        return modelMapper.map(updatedEvent, VirtualEventDto.class);
    }

    public void deleteEvent(Long id) {
        log.info("Deleting virtual event with id: {}", id);
        VirtualEvent event = virtualEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        virtualEventRepository.delete(event);
    }

    public List<VirtualEventDto> searchEvents(String eventName, String platform, LocalDateTime eventDateTime) {
        log.info("Searching virtual events");
        List<VirtualEvent> events = virtualEventRepository.searchEvents(eventName, platform, eventDateTime);
        return events.stream()
                .map(event -> modelMapper.map(event, VirtualEventDto.class))
                .collect(Collectors.toList());
    }

    public List<VirtualEventDto> getEventsByHost(Long hostUserId) {
        log.info("Fetching events by hostUserId: {}", hostUserId);
        return virtualEventRepository.findByHostUserId(hostUserId).stream()
                .map(event -> modelMapper.map(event, VirtualEventDto.class))
                .collect(Collectors.toList());
    }
}
