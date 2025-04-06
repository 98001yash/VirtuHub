package com.company.VirtuHub.EventManagementService.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class VirtualEventDto {
    private Long id;
    private String eventName;
    private LocalDateTime eventDateTime;
    private String platform;
    private String accessLink;
    private String eventDescription;
}

