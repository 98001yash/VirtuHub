package com.company.VirtuHub.EventManagementService.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VirtualEventDto {
    private Long id;
    private String eventName;
    private LocalDateTime eventDateTime;
    private String platform;
    private String accessLink;
    private String eventDescription;

    private Long hostUserId;

}

