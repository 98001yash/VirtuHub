package com.company.VirtuHub.Streaming_service.repository;

import com.company.VirtuHub.Streaming_service.entities.LiveStreamSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveStreamSessionRepository extends JpaRepository<LiveStreamSession,Long> {

    List<LiveStreamSession> findByEventId(Long eventId);
}
