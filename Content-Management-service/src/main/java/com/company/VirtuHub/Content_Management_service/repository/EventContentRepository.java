package com.company.VirtuHub.Content_Management_service.repository;

import com.company.VirtuHub.Content_Management_service.entities.EventContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventContentRepository extends JpaRepository<EventContent,Long> {

    List<EventContent> findByEventId(Long eventId);
}
