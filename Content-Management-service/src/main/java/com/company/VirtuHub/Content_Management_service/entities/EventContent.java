package com.company.VirtuHub.Content_Management_service.entities;


import com.company.VirtuHub.Content_Management_service.enums.ContentStatus;
import com.company.VirtuHub.Content_Management_service.enums.ContentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "event_contents")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    private String title;
    private String description;

    private String contentUrl;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private LocalDateTime uploadedAt;

    @Enumerated(EnumType.STRING)
    private ContentStatus status; //  PENDING, APPROVED, REJECTED

    private String rejectionReason;
}
