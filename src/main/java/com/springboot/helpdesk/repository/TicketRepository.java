package com.springboot.helpdesk.repository;

import com.springboot.helpdesk.dto.response.TicketInfoDto;

import com.springboot.helpdesk.dto.response.TicketInfoDtoV2;
import com.springboot.helpdesk.enums.Priority;
import com.springboot.helpdesk.enums.Status;
import com.springboot.helpdesk.model.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // findBy -- Derived Query Methods
    // Id - findById

    List<Ticket> findByPriority(Priority priority); // select t from Ticket t where t.priority=?1

    List<Ticket> findByStatus(Status status); // select t from Ticket t where t.status=?1

    List<Ticket> findByPriorityAndStatus(Priority priority, Status status);
    // select t from Ticket t where t.priority=?1 and t.status=?2

    @Query("""
           select new com.springboot.helpdesk.dto.response.TicketInfoDto(
           t.id,
           t.subject,
           t.createdAt,t.priority,t.status,c.name,e.name,e.email)
           from Ticket t
           JOIN t.customer c
           JOIN t.executive e
           where c.id = ?1
           """)
    List<TicketInfoDto> getTicketByCustomerId(Long customerId, Pageable pageable);

    @Query("""
            select new com.springboot.helpdesk.dto.response.TicketInfoDtoV2(
            t.id,
            e.id,
            e.name,
            t.subject,
            t.priority,
            t.status,
            t.createdAt,
            c.id,
            c.name
            )
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            JOIN e.user u
            where u.username=?1
            """)
    List<TicketInfoDtoV2> getTicketByCustomerUsername(String customerUsername,Pageable pageable);


    @Query("""
            select new com.springboot.helpdesk.dto.response.TicketInfoDtoV2(
            t.id,
            e.id,
            e.name,
            t.subject,
            t.priority,
            t.status,
            t.createdAt,
            c.id,
            c.name
            )
            from Ticket t
            JOIN t.customer c
            JOIN t.executive e
            JOIN e.user u
            where u.username=?1
            """)

    List<TicketInfoDtoV2> getTicketByExecutiveName(String executiveUserName, Pageable pageable);
}