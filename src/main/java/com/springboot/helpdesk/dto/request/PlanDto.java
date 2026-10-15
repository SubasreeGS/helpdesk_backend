package com.springboot.helpdesk.dto.request;

import com.springboot.helpdesk.enums.PlanType;
import com.springboot.helpdesk.model.Plan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PlanDto (

       @NotNull(message = "Plan is required")
        PlanType planType,


        @NotNull(message = "Price is required")
        Double price
){

}
