package com.example.FinalProjectAllocation.entities;

import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name="employees")


public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    //@Column(name="emp_name")
    private String empName;
    //@Column(nullable = false)
    private String designation;
    private String email;
    private boolean available;

    @Column(name = "skills")
    private String skills;

    @Transient
    private String role;

    //@OneToOne(mappedBy = "employeeEntity", cascade = CascadeType.ALL)
    //private UserEntity userEntity; // Links to User table

    /*public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUser(UserEntity userEntity) {
        this.userEntity = userEntity;
    }*/

    /*@ElementCollection
    private List<String> skills;*/



    @ManyToOne(optional = true) //(cascade = CascadeType.PERSIST)
    @JoinColumn(name="proId", referencedColumnName = "proId", nullable = true)
    private ProjectEntity projectEntity;


    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
