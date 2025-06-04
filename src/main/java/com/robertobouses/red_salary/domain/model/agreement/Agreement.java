package com.robertobouses.red_salary.domain.model.agreement;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Agreement {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private int extraPayments;

    private int numberOfComplements;

    @OneToMany(mappedBy = "agreement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobCategory> jobCategories;

    @OneToMany(mappedBy = "agreement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaryComplement> complements;
}
