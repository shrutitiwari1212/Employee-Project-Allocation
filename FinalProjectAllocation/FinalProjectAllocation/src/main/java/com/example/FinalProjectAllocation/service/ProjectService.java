package com.example.FinalProjectAllocation.service;


import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.repositories.EmployeeRepository;
import com.example.FinalProjectAllocation.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Add a new project
    public ProjectEntity addProject(ProjectEntity projectEntity) {
        System.out.println("Printing project entity: " + projectEntity);
        return projectRepository.save(projectEntity);
    }

    // Get all projects
    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    // Get a project by id
    public ProjectEntity getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    //Delete a project by id
    public String deleteProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
        return "Project with ID " + id + " has been deleted.";
    }

    // Update project details
    public ProjectEntity updateProject(Long proId, ProjectEntity updatedProject) {
        ProjectEntity project = projectRepository.findById(proId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setProName(updatedProject.getProName());
        project.setProDescription(updatedProject.getProDescription());
        project.setCapacity(updatedProject.getCapacity());
        project.setRequiredSkills(updatedProject.getRequiredSkills());

        return projectRepository.save(project);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    // Fetch employees with exact skills required by a project
    public List<EmployeeEntity> getEmployeesWithExactSkills(Long proid) {
        ProjectEntity project = projectRepository.findById(proid)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        String requiredSkills = project.getRequiredSkills();
        return employeeRepository.findEmployeesWithExactSkills(requiredSkills);
    }

    public List<EmployeeEntity> getEmployeesInProject(Long proId) {
        ProjectEntity projectEntity = projectRepository.findById(proId).orElse(null);
        return (projectEntity != null) ? projectEntity.getEmployees() : null;
    }

    public List<ProjectEntity> getProjectsWithoutEmployees() {
        return projectRepository.findByEmployeesIsEmpty();
    }

    public List<ProjectEntity> getUnderstaffedProjects() {
        return projectRepository.findUnderstaffedProjects();
    }

    public List<Long> getAllProjectIds(){
        List<ProjectEntity> projects = projectRepository.findAll();
        List<Long> projectIds = new ArrayList<>();
        for(ProjectEntity projectEntity : projects){
            projectIds.add(projectEntity.getProId());
        }
        return projectIds;
    }
}
