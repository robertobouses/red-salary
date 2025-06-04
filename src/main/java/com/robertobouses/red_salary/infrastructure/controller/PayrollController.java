package com.robertobouses.red_salary.infrastructure.controller;

import com.robertobouses.red_salary.application.service.PayrollService;
import com.robertobouses.red_salary.domain.model.Payroll;
import com.robertobouses.red_salary.infrastructure.controller.dto.PayrollRequest;
import com.robertobouses.red_salary.infrastructure.controller.dto.PayrollResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/payrolls")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generatePayrolls(@RequestBody PayrollRequest request) {
        payrollService.generatePayrollForAllEmployees(request.getMonth(), request.getYear());
        return ResponseEntity.ok("Payrolls generated for month " + request.getMonth());
    }

    @GetMapping
    public ResponseEntity<List<PayrollResponse>> getPayrolls() {
    List<PayrollResponse> payrolls = payrollService.getAllPayrolls();
    return ResponseEntity.ok(payrolls);
}


}
