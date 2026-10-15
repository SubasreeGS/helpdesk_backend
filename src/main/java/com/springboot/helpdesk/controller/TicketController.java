package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.dto.response.TicketInfoDtoV2;
import com.springboot.helpdesk.model.Ticket;
import com.springboot.helpdesk.repository.TicketRepository;
import com.springboot.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor

/*
1-create ticket by customer id
2-manager assign executive to ticket
 */
public class TicketController {
    private final TicketService ticketService;
    private final TicketRepository ticketRepository;


    @PostMapping("/api/ticket/add")
    public void insertTicket(Principal principal,@Valid @RequestBody TicketDto ticketDto) {
        ticketService.insertTicket(principal.getName(),ticketDto);

    }

    @PutMapping("/api/ticket/assign/{executive_id}/{ticket_id}")
    public void assignExecutive(@PathVariable Long executive_id, @PathVariable Long ticket_id) {
        ticketService.assignExecutive(executive_id, ticket_id);

    }


        @GetMapping("/api/ticket/v1/{customer_id}")
       public List<TicketInfoDto> getTicketInfoByCustomerId(@PathVariable("customer_id") Long customerId,
                                                            @RequestParam(name="page") Integer page,
                                                            @RequestParam(name="size") Integer size)

        {

             return ticketService.getTicketInfoByCustomerId(customerId,page,size);
        }



    @GetMapping("/api/ticket/v2")
    public List<TicketInfoDtoV2> getTicketByCustomerUsername(@RequestParam("username") String customerUsername,
                                                             @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return  ticketService.getTicketByCustomerUsername(customerUsername,page,size);

    }

    @GetMapping("/api/ticket/executive")
    public List<TicketInfoDtoV2> getTicketByExecutiveName(@RequestParam("username") String executiveUserName,
                                                        @RequestParam(name="page",required = false,defaultValue = "0") Integer page,
                                                        @RequestParam(name="size",required = false,defaultValue = "5") Integer size)
    {
        return ticketService.getTicketByExecutiveName(executiveUserName,page,size);
    }

}
