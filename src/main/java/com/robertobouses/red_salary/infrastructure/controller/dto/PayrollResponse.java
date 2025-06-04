package com.robertobouses.red_salary.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PayrollResponse {
    private String employeeName;
    private String employeeLastName;
    private String jobTitle;
    private String jobCategoryName;
    private BigDecimal baseSalary;
    private BigDecimal personalComplement;
    private List<SalaryComplementDTO> salaryComplements;
}
