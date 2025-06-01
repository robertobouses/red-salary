package com.robertobouses.red_salary.infrastructure.repository;

import com.robertobouses.red_salary.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
