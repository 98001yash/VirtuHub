package com.company.VirtuHub.EventManagementService.controller;


import com.company.VirtuHub.EventManagementService.dtos.TicketDto;
import com.company.VirtuHub.EventManagementService.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/{eventId}/tickets")
@Slf4j
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@PathVariable Long eventId, @RequestBody TicketDto ticketDTO) {
        log.info("Received request to create ticket for event id: {}", eventId);
        TicketDto createdTicket = ticketService.createTicket(eventId, ticketDTO);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getTicketsByEventId(@PathVariable Long eventId) {
        log.info("Received request to fetch tickets for event id: {}", eventId);
        List<TicketDto> tickets = ticketService.getTicketByEventId(eventId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long ticketId) {
        log.info("Received request to fetch ticket with id: {}", ticketId);
        TicketDto ticket = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long ticketId, @RequestBody TicketDto ticketDTO) {
        log.info("Received request to update ticket with id: {}", ticketId);
        TicketDto updatedTicket = ticketService.updateTicket(ticketId, ticketDTO);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        log.info("Received request to delete ticket with id: {}", ticketId);
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
