package com.robertobouses.red_salary.infrastructure.controller;

import com.robertobouses.red_salary.application.service.RoleService;
import com.robertobouses.red_salary.domain.model.Role;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    public Role create(@RequestBody Role role) {
        return service.create(role);
    }

    @GetMapping
    public List<Role> getAll() {
        return service.findAll();
    }
}
