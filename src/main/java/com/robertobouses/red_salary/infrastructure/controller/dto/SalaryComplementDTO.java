package com.robertobouses.red_salary.infrastructure.controller.dto;

import java.math.BigDecimal;

public class SalaryComplementDTO {
    private String name;
    private BigDecimal amount;

    public SalaryComplementDTO(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
