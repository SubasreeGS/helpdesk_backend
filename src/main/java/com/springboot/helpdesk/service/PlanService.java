package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.PlanDto;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.PlanMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.Plan;
import com.springboot.helpdesk.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    public void addPlan(PlanDto dto) {
        //convert dto to plan obj

        Plan plan = PlanMapper.convertDtoToEntity(dto);
        //save plan
        planRepository.save(plan);
    }
    public Plan findById(long id)
    {
        Optional<Plan> optional = planRepository.findById(id);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("Invalid plan id");

        return optional.get();
    }
}