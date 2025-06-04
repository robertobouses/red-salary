package com.robertobouses.red_salary.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

import com.robertobouses.red_salary.domain.model.agreement.Agreement;
import com.robertobouses.red_salary.domain.model.agreement.JobCategory;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    private String photoUrl;

    private String jobTitle;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;

    @Pattern(
        regexp = "^[A-Z]{2}\\d{2}[A-Z0-9]{1,30}$",
        message = "Invalid bank account format"
    )
    private String bankAccount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE,
        LEAVE,
        FIRED,
        VACATION
    }

    @Column(name = "gross_annual_salary")
    private BigDecimal grossAnnualSalary;

    private BigDecimal personalComplement;

    @ManyToOne
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;
}
