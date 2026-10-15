package com.springboot.helpdesk.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CustomerPlanDto(
        String coupon,
        @NotNull(message = "amount paid is requiered. 0 is also acceptable")
        Double amountPaid,

        @NotNull(message = "customer id is requiered")
        Long customer_id,

        @NotNull(message = "Plan id is requiered")
        Long plan_id

) {
}
