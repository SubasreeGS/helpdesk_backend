package com.springboot.helpdesk.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminDto(
        @NotNull(message ="username is required")
        @NotBlank(message ="username is required")
        String username,

        @NotNull(message ="username is required")
        @NotBlank(message="username is required")
        String password
) {
}
