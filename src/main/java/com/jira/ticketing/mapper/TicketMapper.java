package com.jira.ticketing.mapper;

import com.jira.ticketing.entity.Ticket;
import com.jira.ticketing.entity.dto.TicketDto;

import java.util.Objects;

public class TicketMapper {
    public static Ticket toTicket(TicketDto ticketDto) {
        return Ticket.builder()
                .title(ticketDto.getTitle())
                .description(ticketDto.getDescription())
                .status(ticketDto.getStatus())
                .priority(ticketDto.getPriority())
                .build();
    }

    public static void updateTicket(Ticket ticket, TicketDto ticketDto) {
        if (Objects.nonNull(ticketDto.getTitle())) {
            ticket.setTitle(ticketDto.getTitle());
        }
        if (Objects.nonNull(ticketDto.getDescription())) {
            ticket.setDescription(ticketDto.getDescription());
        }
        if (Objects.nonNull(ticketDto.getStatus())) {
            ticket.setStatus(ticketDto.getStatus());
        }
        if (Objects.nonNull(ticketDto.getPriority())) {
            ticket.setPriority(ticketDto.getPriority());
        }
    }
}
