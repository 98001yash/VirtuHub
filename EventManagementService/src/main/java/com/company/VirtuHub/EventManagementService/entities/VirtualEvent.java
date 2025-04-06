package com.company.VirtuHub.EventManagementService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "virtual_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VirtualEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private LocalDateTime eventDateTime;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private String accessLink;

    @Column
    private String eventDescription;
}
