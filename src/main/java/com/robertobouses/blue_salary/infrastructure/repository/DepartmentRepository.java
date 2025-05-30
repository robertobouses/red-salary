package com.robertobouses.blue_salary.infrastructure.repository;

import com.robertobouses.blue_salary.domain.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
