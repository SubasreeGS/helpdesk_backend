package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.CustomerPlanDto;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.CustomerMapper;
import com.springboot.helpdesk.mapper.CustomerPlanMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.CustomerPlan;
import com.springboot.helpdesk.model.Plan;
import com.springboot.helpdesk.repository.CustomerPlanRepository;
import com.springboot.helpdesk.repository.CustomerRepository;
import com.springboot.helpdesk.repository.PlanRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerPlanService {

    private final CustomerRepository customerRepository;
    private final PlanRepository planRepository;
    private final CustomerPlanRepository customerPlanRepository;

    public void addCustomerPlan( CustomerPlanDto dto) {

        //validate the customer
        Customer customer = customerRepository.findById(dto.customer_id())
                .orElseThrow(()-> new ResourceNotFoundException("Customer ID is invalid"));

        Plan plan = planRepository.findById(dto.plan_id())
                .orElseThrow(()-> new ResourceNotFoundException("Plan ID is invalid"));

        CustomerPlan customerPlan = CustomerPlanMapper.convertDtoToEntity(dto);
        customerPlanRepository.save(customerPlan);

    }
}
