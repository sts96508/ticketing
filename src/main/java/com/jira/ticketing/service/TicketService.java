package com.jira.ticketing.service;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.Ticket;
import com.jira.ticketing.entity.User;
import com.jira.ticketing.entity.dto.TicketDto;
import com.jira.ticketing.exception.TicketNotFoundException;
import com.jira.ticketing.mapper.TicketMapper;
import com.jira.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ProjectService projectService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.projectService = projectService;
        this.userService = userService;
    }

    public Ticket createTicket(Long projectId, TicketDto ticketDto) {
        Project project = projectService.getProjectById(projectId);
        Ticket ticket = TicketMapper.toTicket(ticketDto);
        User user = userService.getUserById(ticketDto.getAssignedUserId());
        ticket.setProject(project);
        ticket.setAssignedUser(user);
        return ticketRepository.save(ticket);

    }

    public List<Ticket> getAllTickets(Long projectId) {
        Project project = projectService.getProjectById(projectId);
        return ticketRepository.findByProject(project);

    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

    public Ticket updateTicket(Long id, TicketDto ticketDto) {
        Ticket ticket = getTicketById(id);
        TicketMapper.updateTicket(ticket, ticketDto);
        User user = ticket.getAssignedUser();
        if ((!Objects.equals(user.getId(), ticketDto.getAssignedUserId()))
                && (Objects.nonNull(ticketDto.getAssignedUserId()))) {
            User newUser = userService.getUserById(ticketDto.getAssignedUserId());
            ticket.setAssignedUser(newUser);

        }
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
