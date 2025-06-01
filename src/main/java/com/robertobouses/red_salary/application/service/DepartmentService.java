package com.robertobouses.red_salary.application.service;

import com.robertobouses.red_salary.domain.model.Department;
import com.robertobouses.red_salary.infrastructure.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department create(Department department) {
        return repository.save(department);
    }

    public List<Department> findAll() {
        return repository.findAll();
    }
}
