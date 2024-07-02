package com.jira.ticketing.controller;

import com.jira.ticketing.entity.Ticket;
import com.jira.ticketing.entity.dto.TicketDto;
import com.jira.ticketing.entity.dto.TicketResponseDto;
import com.jira.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/projects/{projectId}/tickets")
    public ResponseEntity<TicketResponseDto> createTicket(@PathVariable Long projectId, @RequestBody TicketDto ticketDto) {
        TicketResponseDto ticket = ticketService.createTicket(projectId, ticketDto);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @GetMapping("/projects/{projectId}/tickets")
    public ResponseEntity<List<TicketResponseDto>> getAllTickets(@PathVariable Long projectId) {
        List<TicketResponseDto> tickets = ticketService.getAllTickets(projectId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketService.updateTicket(id, ticketDto);
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();

    }


}
