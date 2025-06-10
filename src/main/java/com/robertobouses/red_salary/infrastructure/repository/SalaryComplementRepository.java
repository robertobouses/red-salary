package com.robertobouses.red_salary.infrastructure.repository;

import com.robertobouses.red_salary.domain.model.agreement.SalaryComplement;
import com.robertobouses.red_salary.infrastructure.controller.dto.SalaryComplementDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SalaryComplementRepository extends JpaRepository<SalaryComplement, UUID> {

    @Query("SELECT new com.robertobouses.red_salary.infrastructure.controller.dto.SalaryComplementDTO(sc.name, sc.amount) " +
           "FROM SalaryComplement sc WHERE sc.agreement.id = :agreementId")
    List<SalaryComplementDTO> findSalaryComplementsByAgreementId(@Param("agreementId") UUID agreementId);

}
