package com.company.VirtuHub.Streaming_service.entities;

import com.company.VirtuHub.Streaming_service.enums.StreamStatus;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "live_stream_sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveStreamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    private String streamUrl;
    private String recordingUrl;

    @Enumerated(EnumType.STRING)
    private StreamStatus status;

    private LocalDateTime startDate;

    private LocalDateTime endTime;
}
