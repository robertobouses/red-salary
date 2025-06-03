package com.robertobouses.red_salary.infrastructure.repository;

import com.robertobouses.red_salary.domain.model.agreement.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
}
