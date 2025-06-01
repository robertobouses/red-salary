package com.robertobouses.red_salary.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    private String photoUrl;

    private String jobTitle;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Pattern(
        regexp = "^[A-Z]{2}\\d{2}[A-Z0-9]{1,30}$",
        message = "Invalid bank account format"
    )
    private String bankAccount;

   @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        ACTIVE,
        LEAVE,
        FIRED,
        VACATION
    }

    public Employee() {}

    public Employee(UUID id, String name, String lastName, String email, String photoUrl,
                    String jobTitle, Department department, Role role,
                    String bankAccount, Status status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.photoUrl = photoUrl;
        this.jobTitle = jobTitle;
        this.department = department;
        this.role = role;
        this.bankAccount = bankAccount;
        this.status = status;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
