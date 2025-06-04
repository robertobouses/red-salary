package com.robertobouses.red_salary.application.service;

import com.robertobouses.red_salary.domain.model.Employee;
import com.robertobouses.red_salary.domain.model.Payroll;
import com.robertobouses.red_salary.domain.model.PayrollSalaryComplement;
import com.robertobouses.red_salary.domain.model.agreement.ComplementType;
import com.robertobouses.red_salary.domain.model.agreement.SalaryComplement;
import com.robertobouses.red_salary.infrastructure.repository.EmployeeRepository;
import com.robertobouses.red_salary.infrastructure.repository.PayrollRepository;
import com.robertobouses.red_salary.infrastructure.repository.SalaryComplementRepository;

import org.springframework.stereotype.Service;
import com.robertobouses.red_salary.infrastructure.controller.dto.PayrollResponse;
import com.robertobouses.red_salary.infrastructure.controller.dto.SalaryComplementDTO;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PayrollService {

    private final EmployeeRepository employeeRepository;
    private final PayrollRepository payrollRepository;
    private final SalaryComplementRepository salaryComplementRepository;

    public PayrollService(EmployeeRepository employeeRepository,
                          PayrollRepository payrollRepository,
                          SalaryComplementRepository salaryComplementRepository) {
        this.employeeRepository = employeeRepository;
        this.payrollRepository = payrollRepository;
        this.salaryComplementRepository = salaryComplementRepository;
    }

  public void generatePayrollForAllEmployees(int month, int year) {
    List<Employee> employees = employeeRepository.findAll();

    for (Employee employee : employees) {
        Payroll payroll = Payroll.builder()
                .id(UUID.randomUUID())
                .month(month)
                .year(year)
                .employee(employee)
                .employeeName(employee.getName())
                .employeeLastName(employee.getLastName())
                .jobTitle(employee.getJobTitle())
                .jobCategoryName(employee.getJobCategory().getName())
                .baseSalary(employee.getJobCategory().getBaseSalary())
                .personalComplement(employee.getPersonalComplement() != null ? employee.getPersonalComplement() : BigDecimal.ZERO)
                .build();

        List<SalaryComplement> allComplements = new ArrayList<>();
        allComplements.addAll(employee.getJobCategory().getComplements());
        if (employee.getAgreement() != null && employee.getAgreement().getComplements() != null) {
            allComplements.addAll(employee.getAgreement().getComplements());
        }

        // Construir lista de PayrollSalaryComplement (datos planos)
        List<PayrollSalaryComplement> complementRecords = new ArrayList<>();

        for (SalaryComplement complement : allComplements) {
            BigDecimal amount = BigDecimal.ZERO;
            if (complement.getType() == ComplementType.PERCENTAGE && complement.getPercentage() != null) {
                amount = payroll.getBaseSalary().multiply(complement.getPercentage()).divide(BigDecimal.valueOf(100));
            } else if (complement.getAmount() != null) {
                amount = complement.getAmount();
            }

            PayrollSalaryComplement complementRecord = PayrollSalaryComplement.builder()
                    .name(complement.getName())
                    .amount(amount)
                    .payroll(payroll)
                    .build();

            complementRecords.add(complementRecord);
        }

        payroll.setSalaryComplements(complementRecords);

        payrollRepository.save(payroll);
    }
}


public List<PayrollResponse> getAllPayrolls() {
    return payrollRepository.findAll().stream().map(payroll -> new PayrollResponse(
        payroll.getEmployeeName(),
        payroll.getEmployeeLastName(),
        payroll.getJobTitle(),
        payroll.getJobCategoryName(),
        payroll.getBaseSalary(),
        payroll.getPersonalComplement(),
        payroll.getSalaryComplements().stream()
            .map(c -> new SalaryComplementDTO(c.getName(), c.getAmount()))
            .toList()
    )).toList();
}

}
