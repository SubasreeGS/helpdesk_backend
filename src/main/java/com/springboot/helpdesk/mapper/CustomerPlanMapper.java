package com.springboot.helpdesk.mapper;

import com.springboot.helpdesk.dto.request.CustomerPlanDto;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.CustomerPlan;
import com.springboot.helpdesk.model.Plan;

public class CustomerPlanMapper {
    public static CustomerPlan convertDtoToEntity(CustomerPlanDto dto)
    {
        CustomerPlan customerPlan = new CustomerPlan();
        customerPlan.setCoupon(dto.coupon());
        customerPlan.setAmountPaid(dto.amountPaid());

        Customer customer = new Customer();
        customer.setId(dto.customer_id());
        customerPlan.setCustomer(customer);

        Plan plan = new Plan();
        plan.setId(dto.plan_id());
        customerPlan.setPlan(plan);
        return  customerPlan;
    }
}
