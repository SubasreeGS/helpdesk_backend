package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.ExecutiveDto;
import com.springboot.helpdesk.enums.Role;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.ExecutiveMapper;
import com.springboot.helpdesk.model.Executive;
import com.springboot.helpdesk.model.Manager;
import com.springboot.helpdesk.model.User;
import com.springboot.helpdesk.repository.ExecutiveRepository;
import com.springboot.helpdesk.repository.ManagerRepository;
import com.springboot.helpdesk.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExecutiveService {
    private final ExecutiveRepository executiveRepository;
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void add(@Valid ExecutiveDto executiveDto,String managerUsername) {
        // Step 0: Fetch manager from DB using managerId
        Manager manager =  managerRepository.getByUsername(managerUsername);

        // Step 1: Prepare User object and save it in DB
        /*
        Executive
            User user <-- User must go in DB first and then Executive next
        * */
        User user = userService.getUserObj(  // <-- this is a full created user obj with id
                executiveDto.username(),
                passwordEncoder.encode(executiveDto.password()),
                Role.EXECUTIVE
        );


        // Step Map ExecutiveDto to Executive entity
        Executive executive = ExecutiveMapper.mapDtoToEntity(executiveDto);

        // Step 3: Attach the User & Manager to Executive
        executive.setUser(user);
        executive.setManager(manager);

        // Step 4: Save executive and return
        executiveRepository.save(executive);
    }
}