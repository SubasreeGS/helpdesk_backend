package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.ManagerDto;
import com.springboot.helpdesk.model.Manager;
import com.springboot.helpdesk.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/api/manager/add")
    public void add(@Valid @RequestBody ManagerDto  managerDto)
        {
         managerService.add(managerDto);
        }

}
