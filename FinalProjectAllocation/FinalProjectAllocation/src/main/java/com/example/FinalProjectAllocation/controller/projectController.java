package com.example.FinalProjectAllocation.controller;

import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.repositories.EmployeeRepository;
import com.example.FinalProjectAllocation.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.FinalProjectAllocation.repositories.ProjectRepository;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class projectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeRepository employeeRepository;


    @PostMapping("/addProject")
    public ProjectEntity addProject(@RequestBody ProjectEntity projectEntity) {
        return projectService.addProject(projectEntity);
    }

    @GetMapping("/getAllProjects")
    public List<ProjectEntity> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/getProjectById/{id}")
    public ProjectEntity getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("/deleteProjectById/{id}")
    public String deleteProjectById(@PathVariable Long id) {
        return projectService.deleteProjectById(id);
    }




    @PutMapping("/updateProject/{proId}")
    public ResponseEntity<ProjectEntity> updateProject(@PathVariable Long proId, @RequestBody ProjectEntity updatedProject) {
        return ResponseEntity.ok(projectService.updateProject(proId, updatedProject));  // Calls service method
    }

    @GetMapping("/getEmployeesInProject/{proId}")
    public List<EmployeeEntity> getEmployeesInProject(@PathVariable Long proId) {
        return projectService.getEmployeesInProject(proId);}



    @GetMapping("/getEmployeesWithExactSkills/{proid}")
    public ResponseEntity<List<EmployeeEntity>> getEmployeesWithExactSkills(@PathVariable Long proid) {
        return ResponseEntity.ok(projectService.getEmployeesWithExactSkills(proid));
    }

   /* @GetMapping("/getEmployeesWithPartialSkills{proid}")
    public ResponseEntity<List<EmployeeEntity>> getEmployeesWithPartialSkills(@PathVariable Long proid) {
        ProjectEntity project = projectRepository.findById(proid)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        String requiredSkills = project.getRequiredSkills();
        List<EmployeeEntity> matchingEmployees = employeeRepository.findEmployeesWithMPartialSkills(requiredSkills);

        return ResponseEntity.ok(matchingEmployees);
    }*/

    @GetMapping("/getProjectsWithoutEmployees")
    public List<ProjectEntity> getProjectsWithoutEmployees() {
        return projectService.getProjectsWithoutEmployees();
    }

    @GetMapping("/getUnderStaffedProjects")
    public List<ProjectEntity> getUnderstaffedProjects() {
        return projectService.getUnderstaffedProjects();
    }

    @GetMapping("/getAllProjectIds")
    public List<Long> getAllProjectIds(){
        return projectService.getAllProjectIds();
    }




}
