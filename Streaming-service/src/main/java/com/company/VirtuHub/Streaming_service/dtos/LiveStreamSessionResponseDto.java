package com.company.VirtuHub.Streaming_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveStreamSessionResponseDto {

    private Long id;
    private Long eventId;
    private String streamUrl;
    private String embedCode;
    private String status;
    private Boolean isRecordingEnabled;
    private String recordedVideoUrl;
    private String scheduledStartTime;

}
