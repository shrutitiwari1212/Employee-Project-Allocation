package com.example.FinalProjectAllocation.dto;


public class EmployeeDTO {

    private Long empId;
    private String empName;
    private String designation;
    private boolean available;


    public EmployeeDTO(Long empId, String empName, String designation, boolean available) {
        this.empId = empId;
        this.empName = empName;
        this.designation = designation;
        this.available = available;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
