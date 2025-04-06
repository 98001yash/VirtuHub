package com.company.VirtuHub.EventManagementService.repository;

import com.company.VirtuHub.EventManagementService.entities.VirtualEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirtualEventRepository extends JpaRepository<VirtualEvent,Long> {
    List<VirtualEvent> findByEventName(String eventName);

}
