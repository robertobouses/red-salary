package com.robertobouses.red_salary.application.service;

import com.robertobouses.red_salary.domain.model.agreement.Agreement;
import com.robertobouses.red_salary.domain.model.agreement.SalaryComplement;
import com.robertobouses.red_salary.domain.model.agreement.ComplementType;
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
        if (agreement.getJobCategories() != null) {
            agreement.getJobCategories().forEach(category -> category.setAgreement(agreement));
        }

        if (agreement.getComplements() != null) {
            agreement.getComplements().forEach(complement -> {
                complement.setAgreement(agreement);

                if (complement.getType() == ComplementType.PERCENTAGE && complement.getPercentage() != null) {
                    // Si hay categorías, tomamos la primera como base salarial para calcular el amount
                    if (agreement.getJobCategories() != null && !agreement.getJobCategories().isEmpty()) {
                        complement.updateAmountFromBaseSalary(agreement.getJobCategories().get(0).getBaseSalary());

                        // 👇 Aquí mostramos el complemento calculado por consola
                        System.out.println("Complemento calculado con porcentaje:");
                        System.out.println("Nombre: " + complement.getName());
                        System.out.println("Porcentaje: " + complement.getPercentage());
                        System.out.println("Base Salary: " + agreement.getJobCategories().get(0).getBaseSalary());
                        System.out.println("Amount final: " + complement.getAmount());
                    }
                }
            });
        }

        return agreementRepository.save(agreement);
    }

    public List<Agreement> findAll() {
        return agreementRepository.findAll();
    }
}
