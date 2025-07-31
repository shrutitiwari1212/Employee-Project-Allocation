package com.example.FinalProjectAllocation.controller;

import com.example.FinalProjectAllocation.dto.ProjectDTO;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.repositories.EmployeeRepository;
import com.example.FinalProjectAllocation.repositories.ProjectRepository;
import com.example.FinalProjectAllocation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class employeeController {

    @Autowired
    private ProjectRepository projectRepository;
    //private List<EmployeeDTO> employees = new ArrayList<>();

    /*@GetMapping("/getEmployees")
    public List<employeeEntity> getAllEmployees(){
        return employees;
    }

    @PostMapping("/addEmployees")
    public String addEmployee(@RequestBody employeeEntity employee){
        employees.add(employee);
        return "Employee added successfully";
    }*/

    /*@GetMapping(path = "/getEmployee/{id}")
    public employeeEntity getEmployeeById(@PathVariable int id){
        for(int i =0; i< employees.size(); i++){
            employeeEntity emp = employees.get(i);
            if(emp.getId()==id){
                return emp;
            }
        }
        return null;
    }*/

    /*@RequestMapping(path = "/getEmployees", method = RequestMethod.GET)
    public List<EmployeeDTO> getAllEmployees() {
        return employees;
    }

    @RequestMapping(path = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@RequestBody EmployeeDTO employee) {
        employees.add(employee);
        return "Employee added successfully";
    }

    @RequestMapping(path="/deleteEmployee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable int id){
        //boolean removed = employees.removeIf(emp -> emp.getId() == id);
        //return removed ? "Employee deleted successfully." : "Employee not found";
        for(int i =0; i< employees.size(); i++){
            EmployeeDTO emp = employees.get(i);
            if(emp.getId()==id){
                employees.remove(i);
                return "Employee deleted successfully";
            }
        }
        return "Employee not found";

    }

    @GetMapping(path = "/getEmployee")
    public EmployeeDTO getEmployeeById(@RequestParam int id){
        for(int i =0; i< employees.size(); i++){
            EmployeeDTO emp = employees.get(i);
            if(emp.getId()==id){
                return emp;
            }
        }
        return null;
    }*/

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    /*public employeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }*/

    /*@PostMapping("/addEmployee")
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }
    @GetMapping("/getAllEmployees")
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }*/
    @PostMapping("/addEmployee")
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employeeEntity) {

        if (employeeEntity.getProjectEntity() == null || employeeEntity.getProjectEntity().getProId() == null) {
            employeeEntity.setProjectEntity(null);
        }

        return employeeService.addEmployee(employeeEntity);
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @RequestMapping(value = "/getEmployeeById/{id}", method = RequestMethod.GET)
    public EmployeeEntity getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);

    }

    @DeleteMapping("/deleteEmployeeById/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }


    @GetMapping("/getEmployeesByProjectId/{proid}")
    public List<EmployeeEntity> getEmployeesByProjectId(@PathVariable Long proid) {
        return employeeService.getEmployeesByProjectId(proid);
    }

    @GetMapping("/getEmployeesByDesignation/{designation}")
    public List<EmployeeEntity> getEmployeesByDesignation(@PathVariable String designation) {
        return employeeService.getEmployeesByDesignation(designation);
    }

    @GetMapping("/availableDesignation")
    public List<EmployeeEntity> getAvailableEmployees(@RequestParam String designation) {
        return employeeService.getAvailableEmployeesByDesignation(designation);
    }

    @PutMapping("/updateEmployee/{empId}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long empId, @RequestBody EmployeeEntity updatedEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(empId, updatedEmployee));
    }





       /*
        JdbcAggregateOperations projectRepository = null;
        ProjectEntity project = projectRepository.findById(updatedEmployee.getProjectEntity().getProId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        employee.setProjectEntity(project);*/


    @GetMapping("/employeeSkills/{empId}")
    public ResponseEntity<List<String>> getEmployeeSkills(@PathVariable Long empId) {
        return ResponseEntity.ok(employeeService.getEmployeeSkills(empId)); // Calling service method
    }



//    @GetMapping("/getSkillsForEmployee/{empId}")
//    public ResponseEntity<String> getSkillsForEmployee(@PathVariable String empId) {
//        String skills = employeeService.getSkillsForEmployee(empId);
//        return ResponseEntity.ok(skills);
//    }



    @PutMapping("/updateEmployeeSkills/{empId}")
    public ResponseEntity<String> updateEmployeeSkills(@PathVariable Long empId, @RequestBody List<String> skills) {
        boolean updated = employeeService.updateEmployeeSkills(empId, skills);
        if (updated) {
            return ResponseEntity.ok("Skills updated successfully");
        } else {
            return ResponseEntity.status(404).body("Employee not found");
        }
    }







    @GetMapping("/getProjectByEmployeeId/{empId}")
    public ProjectEntity getProjectByEmployeeId(@PathVariable Long empId){
        return employeeService.getAssignedProject(empId);
    }

    @GetMapping("/getAllAvailableEmployees")
    public List<EmployeeEntity> getAllAvailableEmployees() {
        return employeeService.getAllAvailableEmployees();
    }

//    @GetMapping("/getEmployeesWithoutProject")
//    public List<EmployeeEntity> getEmployeesWithoutProject() {
//        return employeeService.getEmployeesWithoutProject();
//    }


    @GetMapping("/getMatchingProjectsForEmployee/{id}")
    public ResponseEntity<List<ProjectDTO>> getMatchingProjectsForEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getMatchingProjectsForEmployee(id));
    }





}











