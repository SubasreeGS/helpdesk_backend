package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.dto.response.TicketInfoDtoV2;
import com.springboot.helpdesk.enums.Status;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.TicketMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.Executive;
import com.springboot.helpdesk.model.Ticket;
import com.springboot.helpdesk.repository.CustomerRepository;
import com.springboot.helpdesk.repository.ExecutiveRepository;
import com.springboot.helpdesk.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;


    public void insertTicket(String customerName,TicketDto ticketDto) {
      //get customer by customer name
        Customer customer = customerRepository.getUserByUsername(customerName);

        //convert dto to entity
        Ticket ticket = TicketMapper.convertDtoToEntity(ticketDto);


        //add customer id to ticket
        ticket.setCustomer(customer);
        //set status to open
        ticket.setStatus(Status.OPEN);
        //save the ticket in repo
        ticketRepository.save(ticket);
    }

    public void assignExecutive(Long executiveId, Long ticketId) {
        //verify executive id and get obj
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid executive id"));

        //ticket id verify and get obj
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid ticket id"));

        //add exe in ticket
        ticket.setExecutive(executive);

        //save ticket
        ticketRepository.save(ticket);
    }

    public List<TicketInfoDto> getTicketInfoByCustomerId(Long customerId, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page,size);
         return ticketRepository.getTicketByCustomerId(customerId,pageable);

    }

    public List<TicketInfoDtoV2> getTicketByCustomerUsername(String customerUsername, Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return ticketRepository.getTicketByCustomerUsername(customerUsername,pageable);
    }

    public List<TicketInfoDtoV2> getTicketByExecutiveName(String executiveUserName, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return ticketRepository.getTicketByExecutiveName(executiveUserName,pageable);
    }
}
