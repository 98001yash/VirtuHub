package com.company.VirtuHub.Content_Management_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventContentResponseDto {

    private Long id;
    private Long eventId;
    private String title;
    private String description;
    private String contentUrl;
    private String contentType;
    private String uploadedAt;

    private String status;
    private String rejectionReason;

}
