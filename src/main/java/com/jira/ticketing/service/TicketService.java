package com.jira.ticketing.service;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.Ticket;
import com.jira.ticketing.entity.User;
import com.jira.ticketing.entity.dto.TicketDto;
import com.jira.ticketing.entity.dto.TicketResponseDto;
import com.jira.ticketing.exception.TicketNotFoundException;
import com.jira.ticketing.mapper.TicketMapper;
import com.jira.ticketing.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(TicketService.class.getName());

    @Autowired
    public TicketService(TicketRepository ticketRepository, ProjectService projectService, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.projectService = projectService;
        this.userService = userService;
    }

    public TicketResponseDto createTicket(Long projectId, TicketDto ticketDto) {
        Project project = projectService.getProjectById(projectId);
        Ticket ticket = TicketMapper.toTicket(ticketDto);
        User user = userService.getUserById(ticketDto.getAssignedUserId());
        ticket.setProject(project);
        ticket.setAssignedUser(user);
        logger.info("about to save ticketDto");
        log.info("this is from slf4j");
        return TicketMapper.toDto(ticketRepository.save(ticket));

    }

    public List<TicketResponseDto> getAllTickets(Long projectId) {
        Project project = projectService.getProjectById(projectId);
        return TicketMapper.toDto(ticketRepository.findByProject(project));
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
