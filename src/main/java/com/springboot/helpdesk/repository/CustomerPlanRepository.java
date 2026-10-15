package com.springboot.helpdesk.repository;

import com.springboot.helpdesk.model.CustomerPlan;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerPlanRepository extends JpaRepository<CustomerPlan,Long> {
}
