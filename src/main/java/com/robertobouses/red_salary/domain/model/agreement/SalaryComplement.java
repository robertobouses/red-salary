package com.robertobouses.red_salary.domain.model.agreement;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Data
public class SalaryComplement {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ComplementType type;

    private BigDecimal percentage;

    private BigDecimal amount;


    @ManyToOne
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    @ManyToOne
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;

   public void updateAmountFromBaseSalary(BigDecimal baseSalary) {
    if (type == ComplementType.PERCENTAGE && percentage != null) {
        this.amount = baseSalary.multiply(percentage).divide(BigDecimal.valueOf(100));
    }
}
}
