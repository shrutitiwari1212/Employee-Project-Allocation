package com.example.FinalProjectAllocation.dto;


public class ProjectDTO {
    private Long proId;
    private String proName;
    private String proDescription;
    private int capacity;
    private String requiredSkills;
    private double matchPercentage;

    public ProjectDTO(Long proId, String pName, String pDescription, int capacity, String requiredSkills, double matchPercentage) {
        this.proId = proId;
        this.proName= proName;
        this.proDescription = proDescription;
        this.capacity = capacity;
        this.requiredSkills = requiredSkills;
        this.matchPercentage = matchPercentage;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}
