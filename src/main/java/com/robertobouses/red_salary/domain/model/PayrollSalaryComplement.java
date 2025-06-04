package com.robertobouses.red_salary.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "payroll_salary_complements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollSalaryComplement {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payroll_id")
    private Payroll payroll;
}
