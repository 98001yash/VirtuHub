package com.company.VirtuHub.EventManagementService.repository;

import com.company.VirtuHub.EventManagementService.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByVirtualEventId(Long eventId);

}
