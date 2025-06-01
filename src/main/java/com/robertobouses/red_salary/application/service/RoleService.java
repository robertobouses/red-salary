package com.robertobouses.red_salary.application.service;

import com.robertobouses.red_salary.domain.model.Role;
import com.robertobouses.red_salary.infrastructure.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role create(Role role) {
        return repository.save(role);
    }

    public List<Role> findAll() {
        return repository.findAll();
    }
}
