package com.company.VirtuHub.Streaming_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveStreamSessionRequestDto {

    private Long eventId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
