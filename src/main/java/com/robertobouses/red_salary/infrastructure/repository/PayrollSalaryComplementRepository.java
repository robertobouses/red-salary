package com.robertobouses.red_salary.infrastructure.repository;

import com.robertobouses.red_salary.domain.model.PayrollSalaryComplement;
import com.robertobouses.red_salary.infrastructure.controller.dto.SalaryComplementDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PayrollSalaryComplementRepository extends JpaRepository<PayrollSalaryComplement, UUID> {

    @Query("SELECT new com.robertobouses.red_salary.infrastructure.controller.dto.SalaryComplementDTO(pcs.name, pcs.amount) " +
           "FROM PayrollSalaryComplement pcs WHERE pcs.payroll.id = :payrollId")
    List<SalaryComplementDTO> findSalaryComplementsByPayrollId(@Param("payrollId") UUID payrollId);

}
