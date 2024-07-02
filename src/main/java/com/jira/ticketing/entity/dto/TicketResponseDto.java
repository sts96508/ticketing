package com.jira.ticketing.entity.dto;

import com.jira.ticketing.entity.enums.PriorityLevel;
import com.jira.ticketing.entity.enums.TicketStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponseDto {
    private Long id;
    private String title;
    private String description;
    private TicketStatus ticketStatus;
    private PriorityLevel priority;
}
