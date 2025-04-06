package com.company.VirtuHub.EventManagementService.dtos;

import lombok.Data;

@Data
public class TicketDto {
    private Long id;
    private String attendeeName;
    private String attendeeEmail;
    private Long eventId;
    private String ticketType;

    private Long userId;
}
