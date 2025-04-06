package com.company.VirtuHub.EventManagementService.dtos;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class VirtualEventDto {
    private Long id;
    private String eventName;
    private LocalDateTime eventDateTime;
    private String platform;
    private String accessLink;
    private String eventDescription;

    private Long hostUserId;

}

