package com.springboot.helpdesk.service;


import com.springboot.helpdesk.controller.ManagerController;
import com.springboot.helpdesk.dto.request.ExecutiveDto;
import com.springboot.helpdesk.dto.request.ManagerDto;
import com.springboot.helpdesk.enums.Role;
import com.springboot.helpdesk.mapper.ManagerMapper;
import com.springboot.helpdesk.model.Manager;
import com.springboot.helpdesk.model.User;
import com.springboot.helpdesk.repository.ExecutiveRepository;
import com.springboot.helpdesk.repository.ManagerRepository;
import com.springboot.helpdesk.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

private final ManagerRepository managerRepository;
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

public void add(@Valid ManagerDto managerDto)
{
    //step 1: preparing user obj and saving it
    User user = new User();
    user.setUsername(managerDto.username());
    user.setPassword(passwordEncoder.encode(managerDto.password()));
    user.setRole(Role.MANAGER);
    user= userRepository.save(user);

    Manager manager= ManagerMapper.convertDtoToEntity(managerDto);

    manager.setUser(user);

     managerRepository.save(manager);


}


}
