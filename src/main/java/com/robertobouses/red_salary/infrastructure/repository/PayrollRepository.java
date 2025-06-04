package com.robertobouses.red_salary.infrastructure.repository;

import com.robertobouses.red_salary.domain.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PayrollRepository extends JpaRepository<Payroll, UUID> {
}
