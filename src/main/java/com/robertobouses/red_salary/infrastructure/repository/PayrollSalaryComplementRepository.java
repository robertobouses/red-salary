package com.robertobouses.red_salary.infrastructure.repository;

import com.robertobouses.red_salary.domain.model.PayrollSalaryComplement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PayrollSalaryComplementRepository extends JpaRepository<PayrollSalaryComplement, UUID> {
}
