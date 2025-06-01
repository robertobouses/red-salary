package com.robertobouses.red_salary.infrastructure.repository;

import com.robertobouses.red_salary.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
