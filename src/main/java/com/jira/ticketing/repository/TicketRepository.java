package com.jira.ticketing.repository;

import com.jira.ticketing.entity.Project;
import com.jira.ticketing.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //JPA
    List<Ticket> findByProject(Project project);

    //JPQL
    @Query("select t from Ticket t  join t.project p where p.id=:project_Id")
    List<Ticket> fetchByProjectId(@Param("project_Id") Long projectId);

    //native sql
    @Query(value="select * from Ticket t where t.project_id=:project_Id", nativeQuery=true)
    List<Ticket> nativeFetchByProjectId(@Param("project_Id") Long projectId);
}

