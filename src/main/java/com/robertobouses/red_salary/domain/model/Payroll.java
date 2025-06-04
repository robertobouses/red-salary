package com.robertobouses.red_salary.domain.model;

import com.robertobouses.red_salary.domain.model.agreement.SalaryComplement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payrolls")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payroll {

    @Id
    private UUID id;

    private int month;

    private int year;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String employeeName;

    private String employeeLastName;

    private String jobTitle;

    private String jobCategoryName;

    private BigDecimal baseSalary;

    private BigDecimal personalComplement;

    @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL, orphanRemoval = true)
@ToString.Exclude
@EqualsAndHashCode.Exclude
private List<PayrollSalaryComplement> salaryComplements;



}
