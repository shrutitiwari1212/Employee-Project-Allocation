package com.example.FinalProjectAllocation.dto;

public class RoleRequestDTO {

    private Long id;
    private String designation;
    private String status;

    private Long proId;
    private String proName;

    private Long managerEmpId;
    private String managerName;

    private Long selectedEmployeeId;
    private String selectedEmployeeName;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public Long getManagerEmpId() {
        return managerEmpId;
    }

    public void setManagerEmpId(Long managerEmpId) {
        this.managerEmpId = managerEmpId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getSelectedEmployeeId() {
        return selectedEmployeeId;
    }

    public void setSelectedEmployeeId(Long selectedEmployeeId) {
        this.selectedEmployeeId = selectedEmployeeId;
    }

    public String getSelectedEmployeeName() {
        return selectedEmployeeName;
    }

    public void setSelectedEmployeeName(String selectedEmployeeName) {
        this.selectedEmployeeName = selectedEmployeeName;
    }
}
