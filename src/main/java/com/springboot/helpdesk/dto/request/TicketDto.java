package com.springboot.helpdesk.dto.request;

import com.springboot.helpdesk.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketDto(
        @NotBlank(message = "subject is required")
        @NotNull(message = "subject is required")
        String subject,
        @NotBlank(message = "issue is required")
        @NotNull(message = "issue required")
        String issue,
        Priority priority
) {

}