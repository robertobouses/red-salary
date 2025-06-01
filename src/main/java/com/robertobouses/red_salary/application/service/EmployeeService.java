package com.robertobouses.red_salary.application.service;

import com.robertobouses.red_salary.domain.model.Department;
import com.robertobouses.red_salary.domain.model.Employee;
import com.robertobouses.red_salary.domain.model.Employee.Status;
import com.robertobouses.red_salary.domain.model.Role;
import com.robertobouses.red_salary.infrastructure.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(
            String name,
            String lastName,
            String email,
            String photoUrl,
            String jobTitle,
            Department department,
            Role role,
            String bankAccount,
            Status status
    ) {
        UUID id = UUID.randomUUID();
        Employee employee = new Employee(
                id,
                name,
                lastName,
                email,
                photoUrl,
                jobTitle,
                department,
                role,
                bankAccount,
                status
        );
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
