package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.PlanDto;
import com.springboot.helpdesk.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping("/api/plan/add")
    public void createPlan(@Valid @RequestBody PlanDto dto)
    {
        planService.addPlan(dto);
    }

}
