package com.robertobouses.red_salary.domain.model.agreement;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.robertobouses.red_salary.domain.model.Employee;

@Entity
@Data
public class JobCategory {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private BigDecimal baseSalary;
    
    @ManyToOne
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    @OneToMany(mappedBy = "jobCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaryComplement> complements;

    @OneToMany(mappedBy = "jobCategory")
    private List<Employee> employees;

    @PrePersist
    @PreUpdate
    private void calculateComplements() {
        if (complements != null && baseSalary != null) {
            for (SalaryComplement complement : complements) {
                complement.updateAmountFromBaseSalary(baseSalary);
            }
        }
    }
}
