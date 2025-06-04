package com.robertobouses.red_salary.infrastructure.controller.dto;

import lombok.Data;

@Data
public class PayrollRequest {
    private int month;
    private int year;
}
