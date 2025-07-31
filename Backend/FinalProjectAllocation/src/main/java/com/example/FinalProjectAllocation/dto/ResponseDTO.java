package com.example.FinalProjectAllocation.dto;


public class ResponseDTO {
    private String message;
    private String role;
    private Long empId;
    private String empName;
    private Boolean needsPasswordUpdate;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public ResponseDTO(String message, Long empId, String role, String empName) {
        this.message = message;
        this.empId = empId;
        this.role = role;
        this.empName = empName;
    }

    public ResponseDTO() {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Boolean getNeedsPasswordUpdate() {
        return needsPasswordUpdate;
    }

    public void setNeedsPasswordUpdate(Boolean needsPasswordUpdate) {
        this.needsPasswordUpdate = needsPasswordUpdate;
    }
}