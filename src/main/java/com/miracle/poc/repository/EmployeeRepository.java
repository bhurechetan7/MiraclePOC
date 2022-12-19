package com.miracle.poc.repository;

import com.miracle.poc.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Integer> {
}
