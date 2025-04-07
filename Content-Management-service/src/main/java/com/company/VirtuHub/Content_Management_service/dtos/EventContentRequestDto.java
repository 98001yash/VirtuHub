package com.company.VirtuHub.Content_Management_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventContentRequestDto {

    private Long eventId;
    private String title;
    private String description;
    private String contentUrl;
    private String contentType;  // VIDEO, DOCUMENT
}
