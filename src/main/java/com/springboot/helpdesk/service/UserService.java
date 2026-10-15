package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.AdminDto;
import com.springboot.helpdesk.enums.Role;
import com.springboot.helpdesk.model.User;
import com.springboot.helpdesk.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User getUserObj(String username, String password, Role role){
        User user = new User(username,password,role);

        return userRepository.save(user);
    }

    public void addAdmin(@Valid AdminDto dto) {
        //prepare user using Admin dto

        User user = new User(
                dto.username(),
                passwordEncoder.encode(dto.password()),
                Role.ADMIN);

        //save user in db
        userRepository.save(user);

    }
}