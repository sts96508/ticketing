package com.jira.ticketing.service;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.Ticket;
import com.jira.ticketing.entity.Users;
import com.jira.ticketing.entity.dto.TicketDto;
import com.jira.ticketing.entity.dto.TicketResponseDto;
import com.jira.ticketing.mapper.TicketMapper;
import com.jira.ticketing.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createTicket() {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setTitle("ticket1");
        ticketDto.setAssignedUserId(20L);
        ticketDto.setDescription("some ticket");

        Project project = new Project();
        when(projectService.getProjectById(anyLong())).thenReturn(project);

        Users user = new Users();
        when(userService.getUserById(anyLong())).thenReturn(user);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(TicketMapper.toTicket(ticketDto));
        TicketResponseDto result = ticketService.createTicket(123L, ticketDto);
        assertNotNull(result);
        assertEquals(ticketDto.getTitle(), result.getTitle());
        verify(userService).getUserById(eq(ticketDto.getAssignedUserId()));
        verify(projectService).getProjectById(eq(123L));
        verify(ticketRepository).save(any(Ticket.class));
    }

    @Test
    void getAllTickets() {
    }

    @Test
    void getTicketById() {
    }

    @Test
    void updateTicket() {
    }

    @Test
    void deleteTicket() {
    }
}