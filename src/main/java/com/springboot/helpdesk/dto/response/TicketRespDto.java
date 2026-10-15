package com.springboot.helpdesk.dto.response;

import com.springboot.helpdesk.enums.Priority;
import com.springboot.helpdesk.enums.Status;

import java.time.Instant;

public record TicketRespDto(
        Long tickeId,
        String subject,
        Instant createdAt,
        Priority priority,
        Status status
) {
}
