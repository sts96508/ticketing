package com.jira.ticketing.mapper;

import com.jira.ticketing.entity.Ticket;
import com.jira.ticketing.entity.dto.TicketDto;
import com.jira.ticketing.entity.dto.TicketResponseDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static TicketResponseDto toDto(Ticket ticket) {
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .ticketStatus(ticket.getStatus())
                .priority(ticket.getPriority())
                .build();
    }

    public static List<TicketResponseDto> toDto(List<Ticket> tickets) {
//        return tickets.stream().map(ticket->toDto(ticket)).collect(Collectors.toList());
        return tickets.stream().map(TicketMapper::toDto).collect(Collectors.toList());

    }
}
