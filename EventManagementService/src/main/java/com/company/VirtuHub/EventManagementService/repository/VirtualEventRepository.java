package com.company.VirtuHub.EventManagementService.repository;

import com.company.VirtuHub.EventManagementService.entities.VirtualEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VirtualEventRepository extends JpaRepository<VirtualEvent,Long> {
    List<VirtualEvent> findByEventName(String eventName);

    List<VirtualEvent> findByEventNameContainingIgnoreCase(String eventName);

    List<VirtualEvent> findByPlatformContainingIgnoreCase(String platform);

    List<VirtualEvent> findByEventDateTime(LocalDateTime dateTime);

    @Query("SELECT ve FROM VirtualEvent ve WHERE " +
            "(:eventName IS NULL OR LOWER(ve.eventName) LIKE LOWER(CONCAT('%', :eventName, '%'))) AND " +
            "(:platform IS NULL OR LOWER(ve.platform) LIKE LOWER(CONCAT('%', :platform, '%'))) AND " +
            "(:eventDateTime IS NULL OR ve.eventDateTime = :eventDateTime)")
    List<VirtualEvent> searchEvents(String eventName, String platform, LocalDateTime eventDateTime);
}
