package com.robertobouses.red_salary.application.service;

import com.robertobouses.red_salary.domain.model.Department;
import com.robertobouses.red_salary.domain.model.Employee;
import com.robertobouses.red_salary.domain.model.Employee.Status;
import com.robertobouses.red_salary.domain.model.Role;
import com.robertobouses.red_salary.domain.model.agreement.JobCategory;
import com.robertobouses.red_salary.infrastructure.repository.DepartmentRepository;
import com.robertobouses.red_salary.infrastructure.repository.EmployeeRepository;
import com.robertobouses.red_salary.infrastructure.repository.JobCategoryRepository;
import com.robertobouses.red_salary.infrastructure.repository.RoleRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final JobCategoryRepository jobCategoryRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            RoleRepository roleRepository,
            DepartmentRepository departmentRepository,
            JobCategoryRepository jobCategoryRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.departmentRepository = departmentRepository;
        this.jobCategoryRepository = jobCategoryRepository;
    }

    public Employee createEmployee(
        String name,
        String lastName,
        String email,
        String photoUrl,
        String jobTitle,
        Long departmentId,
        Long roleId,
        UUID jobCategoryId,
        String bankAccount,
        Status status,
        BigDecimal grossAnnualSalary
) {
    Department department = departmentRepository.findById(departmentId)
            .orElseThrow(() -> new IllegalArgumentException("Department not found"));

    Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new IllegalArgumentException("Role not found"));

    JobCategory jobCategory = jobCategoryRepository.findById(jobCategoryId)
            .orElseThrow(() -> new IllegalArgumentException("Job category not found"));

    BigDecimal convenioSalary = calculateMinimumSalary(jobCategory);

    // Si grossAnnualSalary es null, se usa el mínimo convenio
    if (grossAnnualSalary == null) {
        grossAnnualSalary = convenioSalary;
    } else {
        // Compara y toma el mayor entre lo enviado y lo del convenio
        if (grossAnnualSalary.compareTo(convenioSalary) < 0) {
            grossAnnualSalary = convenioSalary;
        }
    }

    Employee employee = new Employee(
        UUID.randomUUID(),
        name,
        lastName,
        email,
        photoUrl,
        jobTitle,
        department,
        role,
        jobCategory,
        bankAccount,
        status,
        grossAnnualSalary
    );

    return employeeRepository.save(employee);
}

private BigDecimal calculateMinimumSalary(JobCategory jobCategory) {
    BigDecimal baseSalary = jobCategory.getBaseSalary() != null ? jobCategory.getBaseSalary() : BigDecimal.ZERO;

    BigDecimal complementsTotal = BigDecimal.ZERO;
    if (jobCategory.getComplements() != null) {
        for (var complement : jobCategory.getComplements()) {
            if (complement.getAmount() != null) {
                complementsTotal = complementsTotal.add(complement.getAmount());
            }
        }
    }

    // Solo 12 pagas, sin extras por ahora
    int basePayments = 12;

    return baseSalary.add(complementsTotal).multiply(BigDecimal.valueOf(basePayments));
}}