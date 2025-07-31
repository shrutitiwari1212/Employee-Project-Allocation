package com.example.FinalProjectAllocation.service;

import com.example.FinalProjectAllocation.dto.ProjectDTO;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.entities.UserEntity;
import com.example.FinalProjectAllocation.repositories.EmployeeRepository;
import com.example.FinalProjectAllocation.repositories.ProjectRepository;
import com.example.FinalProjectAllocation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProjectRepository projectRepository;




    // Method to add an employee
//    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
//        return employeeRepository.save(employeeEntity);
//    }
    @Autowired
    private JavaMailSender mailSender;
    public void sendCredentialsEmail(String toEmail, String username, String password, String empName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Your Login Credentials");
            message.setText("Hi " + empName + ",\n\n" + "Your account has been created successfully.\n\n"
                    + "Username: " + username + "\n"
                    + "Password: " + password + "\n\n"
                    + "Please log in to the employee portal and update your password.\n\n" + "Regards, \nAdmin Team");
            mailSender.send(message);
            System.out.println("Email successfully sent to: " + toEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email to:" + toEmail);
        }
    }


    public EmployeeEntity addEmployee(EmployeeEntity employeeEntity) {
        System.out.println("EmployeeEntity in add employee for admin"+ employeeEntity);
        if (employeeEntity.getProjectEntity() != null && employeeEntity.getProjectEntity().getProId() != null){
            Long projectId = employeeEntity.getProjectEntity().getProId();
            ProjectEntity projectEntity = projectRepository.findById(projectId).orElse(null);
            employeeEntity.setProjectEntity(projectEntity);
        }
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        UserEntity user = new UserEntity();
        user.setUsername(savedEmployee.getEmpName().toLowerCase().replaceAll("\\s","") + savedEmployee.getEmpId());
        user.setPassword("temp123");
        user.setRole(employeeEntity.getRole());
        user.setEmployeeEntity(savedEmployee);

        userRepository.save(user);

        try{
            sendCredentialsEmail(employeeEntity.getEmail(), user.getUsername(), user.getPassword(), employeeEntity.getEmpName());
        } catch (Exception e) {
            System.out.println("Failed to send email to : " + savedEmployee.getEmail());
            //e.printStackTrace();
        }
        return savedEmployee;
    }


    // Method to get all employees
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Method to get an employee by id
    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    //Method to delete an employee by id
    public String deleteEmployeeById(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        UserEntity userEntity = userRepository.findByEmployeeEntity(employee);
        if(userEntity != null){
            userRepository.delete(userEntity);
        }
        employeeRepository.deleteById(id);
        return "Employee with id " + id + " has been deleted.";
    }

    //Method to get employees by project ID
    public List<EmployeeEntity> getEmployeesByProjectId(Long proid) {
        return employeeRepository.findByProjectEntity_proId(proid);
    }

    // Method to get employees by designation
    public List<EmployeeEntity> getEmployeesByDesignation(String designation) {
        return employeeRepository.findByDesignation(designation);
    }


    // get employees available by designation
    public List<EmployeeEntity> getAvailableEmployeesByDesignation(String designation) {
        return employeeRepository.findAvailableEmployeesByDesignation(designation);
    }

    // Update employee details
    public EmployeeEntity updateEmployee(Long empId, EmployeeEntity updatedEmployee) {
        EmployeeEntity employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setEmpName(updatedEmployee.getEmpName());
        employee.setDesignation(updatedEmployee.getDesignation());
        employee.setAvailable(updatedEmployee.isAvailable());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setSkills(updatedEmployee.getSkills());

        return employeeRepository.save(employee);
    }

    // get employee skills by ID
    public List<String> getEmployeeSkills(Long empId) {
        EmployeeEntity employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return Arrays.asList(employee.getSkills().split(","));
    }



    public boolean updateEmployeeSkills(Long empId, List<String> skills) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(empId);
        if (employeeOpt.isPresent()) {
            EmployeeEntity employee = employeeOpt.get();

            List<String> cleanedSkills = skills.stream()
                    .map(String::trim)
                    .distinct()
                    .collect(Collectors.toList());

            employee.setSkills(String.join(",",cleanedSkills ));
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }





    public Map<String, Object> getProjectDetailsByEmployeeId(Long empId) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(empId);

        if (employeeOpt.isEmpty()) {
            return Collections.emptyMap();
        }

        EmployeeEntity employee = employeeOpt.get();
        ProjectEntity project = employee.getProjectEntity();

        if (project == null) {
            return Collections.emptyMap();
        }

        List<EmployeeEntity> teamMembers = employeeRepository.findByProjectEntity(project);

        Map<String, Object> response = new HashMap<>();
        response.put("project", project);
        response.put("teamMembers", teamMembers);

        return response;
    }

    public ProjectEntity getAssignedProject(Long empId){
        EmployeeEntity employeeEntity = employeeRepository.findByEmpId(empId);
        if(employeeEntity != null){
            return employeeEntity.getProjectEntity();
        }
        return null;
    }

    public List<EmployeeEntity> getAllAvailableEmployees() {
        return employeeRepository.findByAvailableTrue();
    }




    public double calculateSkillMatchPercentage(String employeeSkills, String projectSkills) {
        String[] empSkillsArray = employeeSkills.split(",");
        String[] projectSkillsArray = projectSkills.split(",");

        int matchedSkillsCount = 0;
        int totalProjectSkillsCount = projectSkillsArray.length;

        for (String projectSkill : projectSkillsArray) {
            for (String empSkill : empSkillsArray) {
                if (projectSkill.trim().equalsIgnoreCase(empSkill.trim())) {
                    matchedSkillsCount++;
                    break;
                }
            }
        }
        return totalProjectSkillsCount > 0 ? (matchedSkillsCount / (double) totalProjectSkillsCount) * 100 : 0.0;
    }

    public List<ProjectDTO> getMatchingProjectsForEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            List<ProjectDTO> matchedProjects = new ArrayList<>();

            for (ProjectEntity project : projectRepository.findAll()) {
                double matchPercentage = calculateSkillMatchPercentage(employee.getSkills(), project.getRequiredSkills());

                if (matchPercentage > 0) {
                    ProjectDTO projectDTO = new ProjectDTO(
                            project.getProId(),
                            project.getProName(),
                            project.getProDescription(),
                            project.getCapacity(),
                            project.getRequiredSkills(),
                            matchPercentage
                    );
                    matchedProjects.add(projectDTO);
                }
            }
            return matchedProjects;
        }
        return Collections.emptyList();
    }


//    public List<EmployeeEntity> getEmployeesWithoutProject() {
//        return employeeRepository.findByProjectEntityIsNull();}





}






