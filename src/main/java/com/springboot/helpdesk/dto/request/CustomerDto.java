package com.springboot.helpdesk.dto.request;

public record CustomerDto(
        String name,
        String city,
        String email,
        String username,
        String password

){

}

