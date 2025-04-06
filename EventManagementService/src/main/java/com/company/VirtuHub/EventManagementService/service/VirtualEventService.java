package com.company.VirtuHub.EventManagementService.service;



import com.company.VirtuHub.EventManagementService.dtos.VirtualEventDto;
import com.company.VirtuHub.EventManagementService.entities.VirtualEvent;
import com.company.VirtuHub.EventManagementService.exceptions.ResourceNotFoundException;
import com.company.VirtuHub.EventManagementService.repository.VirtualEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VirtualEventService {

    private final VirtualEventRepository virtualEventRepository;
    private final ModelMapper modelMapper;

    public VirtualEventDto createEvent(VirtualEventDto eventDto){
        log.info("Creating a new Virtual event: {}",eventDto.getEventName());

        VirtualEvent event = modelMapper.map(eventDto, VirtualEvent.class);
        VirtualEvent savedEvent = virtualEventRepository.save(event);
        return modelMapper.map(savedEvent, VirtualEventDto.class);
    }

    public List<VirtualEventDto> getAllEvents(){
        log.info("Fetching all virtual events");
        List<VirtualEvent> events = virtualEventRepository.findAll();
        return events.stream()
                .map(event->modelMapper.map(event, VirtualEventDto.class))
                .collect(Collectors.toList());
    }

    public VirtualEventDto getEventById(Long id){
        log.info("Fetching virtual event with id: {}",id);
        VirtualEvent event = virtualEventRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Event not found with id: "+id));
        return modelMapper.map(event, VirtualEventDto.class);
    }

    public VirtualEventDto updateEvent(Long id, VirtualEventDto eventDetails){
        log.info("Updating virtual event with id: {}",id);
        VirtualEvent existingEvent = virtualEventRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Event not found with id: "+id));
        modelMapper.map(eventDetails, existingEvent);
        VirtualEvent updatedEvent = virtualEventRepository.save(existingEvent);
        return modelMapper.map(updatedEvent, VirtualEventDto.class);
    }


    public void deleteEvent(Long id){
        log.info("Deleting virtual event with id: {}",id);
        VirtualEvent event = virtualEventRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Event not found with id: "+id));
        virtualEventRepository.delete(event);
    }
}
