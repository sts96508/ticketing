package com.jira.ticketing.entity.dto;


import com.jira.ticketing.entity.enums.PriorityLevel;
import com.jira.ticketing.entity.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private String title;
    private String description;
    private TicketStatus status;
    private PriorityLevel priority;
    private Long assignedUserId;

}
