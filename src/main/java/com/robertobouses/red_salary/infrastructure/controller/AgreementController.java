package com.robertobouses.red_salary.infrastructure.controller;

import com.robertobouses.red_salary.domain.model.agreement.Agreement;
import com.robertobouses.red_salary.domain.model.agreement.JobCategory;
import com.robertobouses.red_salary.domain.model.agreement.SalaryComplement;
import com.robertobouses.red_salary.infrastructure.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/agreements")
public class AgreementController {

    @Autowired
    private AgreementRepository agreementRepository;

    @PostMapping
    public ResponseEntity<Agreement> createAgreement(@RequestBody Agreement agreement) {

        if (agreement.getJobCategories() != null) {
            for (JobCategory category : agreement.getJobCategories()) {
                category.setAgreement(agreement);
            }
        }

        if (agreement.getComplements() != null) {
            for (SalaryComplement complement : agreement.getComplements()) {
                complement.setAgreement(agreement);
            }
        }

        System.out.println("Agreement-level complements:");
        for (SalaryComplement c : agreement.getComplements()) {
            System.out.println("-> " + c.getName() + " | type: " + c.getType() + " | amount: " + c.getAmount());
        }

        Agreement saved = agreementRepository.save(agreement);
        return ResponseEntity.created(URI.create("/api/agreements/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Agreement> getAllAgreements() {
        return agreementRepository.findAll();
    }
}
