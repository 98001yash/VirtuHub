package com.company.VirtuHub.Streaming_service.dtos;


import com.company.VirtuHub.Streaming_service.enums.StreamStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveStreamSessionResponseDto {

    private Long id;
    private Long eventId;
    private String streamUrl;
    private String recordingUrl;
    private StreamStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
