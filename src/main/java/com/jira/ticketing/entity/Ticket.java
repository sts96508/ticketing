package com.jira.ticketing.entity;

import com.jira.ticketing.entity.enums.PriorityLevel;
import com.jira.ticketing.entity.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private PriorityLevel priority;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="project_id", nullable = false)
    private Project project;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="assigned_user_id", nullable = false)
    private User assignedUser;

}

