package com.springboot.helpdesk;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.enums.Priority;
import com.springboot.helpdesk.enums.Status;
import com.springboot.helpdesk.repository.TicketRepository;
import com.springboot.helpdesk.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;

    @Test
    public void getTicketByCustomerIdTest()
    {
        TicketInfoDto dto1=new TicketInfoDto(1L,"test1", Instant.now(), Priority.LOW, Status.OPEN,"cus1","exe1","exe1@gmail.com");
        TicketInfoDto dto2=new TicketInfoDto(2L,"test2", Instant.now(), Priority.HIGH, Status.OPEN,"cus2","exe2","exe3@gmail.com");
        TicketInfoDto dto3=new TicketInfoDto(3L,"test3", Instant.now(), Priority.HIGH, Status.OPEN,"cus3","exe3","exe3@gmail.com");

        when(ticketRepository.getTicketByCustomerId(1L, PageRequest.of(0,5)))
                .thenReturn(List.of(dto1,dto2));
        when(ticketRepository.getTicketByCustomerId(2L, PageRequest.of(0,5)))
                .thenReturn(List.of(dto3));

        assertEquals(2,ticketService.getTicketInfoByCustomerId(1L,0,5).size());
        assertEquals(1,ticketService.getTicketInfoByCustomerId(2L,0,5).size());

    }
}
