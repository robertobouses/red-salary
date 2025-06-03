package com.robertobouses.red_salary.application.service;

import com.robertobouses.red_salary.domain.model.agreement.Agreement;
import com.robertobouses.red_salary.infrastructure.repository.AgreementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgreementService {

    private final AgreementRepository agreementRepository;

    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    public Agreement save(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    public List<Agreement> findAll() {
        return agreementRepository.findAll();
    }
}
