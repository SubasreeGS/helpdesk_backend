package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.CustomerDto;
import com.springboot.helpdesk.enums.Role;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.CustomerMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.User;
import com.springboot.helpdesk.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void insertCustomer(CustomerDto dto) {
        //s1- prepare the user obj
       User user =  userService.getUserObj(dto.username(),dto.password(), Role.CUSTOMER);

       //step 1.5
        user.setPassword(passwordEncoder.encode(user.getPassword()));

       //convert dto to customer
        Customer customer=CustomerMapper.ConvertDtoToEntity(dto);
        //add user to customer
        customer.setUser(user);
        //add customer to repository
        customerRepository.save(customer);

    }

    public Customer getById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if(optional.isEmpty())
            throw new ResourceNotFoundException("Invalid customer id");

        return optional.get();
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public void deleteById(long id) {
        getById(id);
        customerRepository.deleteById(id);
    }

    
}
