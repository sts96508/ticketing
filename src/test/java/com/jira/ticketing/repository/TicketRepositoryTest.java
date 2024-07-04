package com.jira.ticketing.repository;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.Ticket;
import com.jira.ticketing.entity.Users;
import com.jira.ticketing.utils.AppConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;
    private Ticket savedTicket1;
    private Ticket savedTicket2;

    @BeforeEach
    void setUp() {

        Users user1 = new Users();
        user1.setUsername(AppConstants.UsersConstants.USERNAME);
        user1.setPassword(AppConstants.UsersConstants.PASSWORD);
        user1.setEmail(AppConstants.UsersConstants.EMAIL);
        user1.setRole(AppConstants.UsersConstants.ROLE);

        Users user2 = new Users();
        user2.setUsername(AppConstants.UsersConstants.USERNAME1);
        user2.setPassword(AppConstants.UsersConstants.PASSWORD1);
        user2.setEmail(AppConstants.UsersConstants.EMAIL1);
        user2.setRole(AppConstants.UsersConstants.ROLE1);

        Project project = new Project();
        project.setName(AppConstants.PROJECTNAME);
        project.setDescription(AppConstants.PROJECTDESCRIPTION);
        project.setOwner(user1);

        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();

        ticket1.setTitle(AppConstants.TicketConstants.TITLE1);
        ticket1.setDescription(AppConstants.TicketConstants.DESCRIPTION1);
        ticket1.setStatus(AppConstants.TicketConstants.STATUS1);
        ticket1.setPriority(AppConstants.TicketConstants.PRIORITY1);
        ticket1.setProject(project);
        ticket1.setAssignedUsers(user1);

        ticket2.setTitle(AppConstants.TicketConstants.TITLE2);
        ticket2.setDescription(AppConstants.TicketConstants.DESCRIPTION2);
        ticket2.setStatus(AppConstants.TicketConstants.STATUS2);
        ticket2.setPriority(AppConstants.TicketConstants.PRIORITY2);
        ticket2.setProject(project);
        ticket2.setAssignedUsers(user2);

        savedTicket1 = ticketRepository.save(ticket1);
        savedTicket2 = ticketRepository.save(ticket2);

    }

    @Test
    void findByProject() {
        List<Ticket> tickets = ticketRepository.findByProject(savedTicket1.getProject());
        assertNotNull(tickets);
        assertEquals(2, tickets.size());
        for (Ticket t : tickets) {
            if (Objects.equals(t.getId(), savedTicket1.getId())) {
                validateSameTicket(savedTicket1, t);
            } else {
                validateSameTicket(savedTicket2, t);
            }
        }
    }

    @Test
    void findByProjectNotFound() {
        Project dummy = new Project();
        dummy.setId(999L);
        List<Ticket> tickets = ticketRepository.findByProject(dummy);
        assertNotNull(tickets);
        assertTrue(tickets.isEmpty());

    }


    private void validateSameTicket(Ticket t1, Ticket t2) {
        assertEquals(t1.getId(), t2.getId());
    }
}