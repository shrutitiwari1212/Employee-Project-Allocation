package com.example.FinalProjectAllocation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.ClientInfoStatus;
import java.util.List;

@Entity
@Table(name = "projects")


public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proId;

    //@Column(name="pro_name")
    private String proName;
    private String proDescription;
    private int capacity;

    @Column(name = "required_skills")
    private String requiredSkills;


    @OneToMany(mappedBy = "projectEntity"/*, cascade = CascadeType.ALL, orphanRemoval = true*/)
    @JsonIgnore
    private List<EmployeeEntity> employees;

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    /*@OneToMany(mappedBy = "projectEntity")
    private List<EmployeeEntity> employeeEntity; */


}
