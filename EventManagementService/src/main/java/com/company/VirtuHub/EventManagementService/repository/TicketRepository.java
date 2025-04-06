package com.company.VirtuHub.EventManagementService.repository;

import com.company.VirtuHub.EventManagementService.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
