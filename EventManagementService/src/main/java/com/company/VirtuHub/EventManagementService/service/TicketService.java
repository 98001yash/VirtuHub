package com.company.VirtuHub.EventManagementService.service;

import com.company.VirtuHub.EventManagementService.dtos.TicketDto;
import com.company.VirtuHub.EventManagementService.entities.Ticket;
import com.company.VirtuHub.EventManagementService.entities.VirtualEvent;
import com.company.VirtuHub.EventManagementService.exceptions.ResourceNotFoundException;
import com.company.VirtuHub.EventManagementService.repository.TicketRepository;
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
public class TicketService {

    private final TicketRepository ticketRepository;
    private final VirtualEventRepository virtualEventRepository;
    private final ModelMapper modelMapper;

    public TicketDto bookTicket(Long eventId, TicketDto ticketDto){
        log.info("Creating ticket for event id: {}", eventId);
        VirtualEvent event = virtualEventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + eventId));

        // TODO: Implement payment integration here
        // For example: Call PaymentService and verify payment before booking the ticket.

        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        ticket.setVirtualEvent(event);
        Ticket savedTicket = ticketRepository.save(ticket);

        // TODO: Send booking confirmation notification to the user (Email/WhatsApp/etc.)
        // For example: Call NotificationService with booking details.

        return modelMapper.map(savedTicket, TicketDto.class);
    }


    public List<TicketDto> getTicketByEventId(Long eventId){
        log.info("Fetching ticket for event id: {}",eventId);
        List<Ticket> tickets = ticketRepository.findByVirtualEventId(eventId);
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }
    public TicketDto getTicketById(Long id) {
        log.info("Fetching ticket with id: {}", id);
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        return modelMapper.map(ticket, TicketDto.class);
    }

    public TicketDto updateTicket(Long id, TicketDto ticketDetails) {
        log.info("Updating ticket with id: {}", id);
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));

        modelMapper.map(ticketDetails, existingTicket);
        Ticket updatedTicket = ticketRepository.save(existingTicket);
        return modelMapper.map(updatedTicket, TicketDto.class);
    }

    public void deleteTicket(Long id) {
        log.info("Deleting ticket with id: {}", id);
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found with id: " + id));
        ticketRepository.delete(ticket);
    }

}
