package com.robertobouses.red_salary.infrastructure.controller;

import com.robertobouses.red_salary.application.service.DepartmentService;
import com.robertobouses.red_salary.domain.model.Department;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public Department create(@RequestBody Department department) {
        return service.create(department);
    }

    @GetMapping
    public List<Department> getAll() {
        return service.findAll();
    }
}
