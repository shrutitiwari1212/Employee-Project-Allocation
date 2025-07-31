package com.example.FinalProjectAllocation.entities;

import jakarta.persistence.*;
@Entity
@Table(name="open_position_request")
public class RoleRequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pro_id", nullable = false)
    private ProjectEntity project;

    @Column(nullable = false)
    private String designation;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private EmployeeEntity manager;

    @Column(nullable = false)
    private String status = "Pending";

    @ManyToOne
    @JoinColumn(name="selected_employee_id")
    private EmployeeEntity selectedEmployee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public EmployeeEntity getManager() {
        return manager;
    }

    public void setManager(EmployeeEntity manager) {
        this.manager = manager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeEntity getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(EmployeeEntity selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
}
