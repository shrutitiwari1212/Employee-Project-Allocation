package com.example.FinalProjectAllocation.controller;

import com.example.FinalProjectAllocation.dto.EmployeeMatchDTO;
import com.example.FinalProjectAllocation.dto.RoleRequestDTO;
import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import com.example.FinalProjectAllocation.entities.RoleRequestEntity;
import com.example.FinalProjectAllocation.repositories.EmployeeRepository;
import com.example.FinalProjectAllocation.repositories.ProjectRepository;
import com.example.FinalProjectAllocation.repositories.RoleRequestRepository;
import com.example.FinalProjectAllocation.service.RoleRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class roleRequestController {

    @Autowired
    private RoleRequestService roleRequestService;

    @Autowired
    private RoleRequestRepository roleRequestRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/raiseRequest")
    public ResponseEntity<RoleRequestEntity> raiseRequest(@RequestBody RoleRequestEntity request){
        RoleRequestEntity savedRequest = roleRequestService.saveRoleRequest(request);
        return ResponseEntity.ok(savedRequest);
    }

//    @PostMapping("/raiseRequest")
//    public ResponseEntity<RoleRequestEntity> raiseRequest(@RequestBody RoleRequestDTO dto) {
//        RoleRequestEntity entity = new RoleRequestEntity();
//
//        entity.setDesignation(dto.getDesignation());
//        entity.setStatus("Pending");
//
//        // Fetch manager and project from repositories
//        EmployeeEntity manager = employeeRepository.findById(dto.getManagerEmpId())
//                .orElseThrow(() -> new RuntimeException("Manager not found"));
//        entity.setManager(manager);
//
//        ProjectEntity project = projectRepository.findById(dto.getProId())
//                .orElseThrow(() -> new RuntimeException("Project not found"));
//        entity.setProject(project);
//
//        RoleRequestEntity savedRequest = roleRequestService.saveRoleRequest(entity);
//        return ResponseEntity.ok(savedRequest);
//    }


    @GetMapping("/getAllPendingRequests")
    public ResponseEntity<List<RoleRequestDTO>> getAllPendingRequests() {
        List<RoleRequestDTO> pendingRequests = roleRequestService.getPendingRequests();
        return ResponseEntity.ok(pendingRequests);
    }

    @GetMapping ("/getPendingRequestCount")
    public ResponseEntity<Long> getPendingRequestCount(){
        Long count = roleRequestRepository.countByStatus("Pending");
        return ResponseEntity.ok(count);
    }

    @GetMapping("/getEligibleEmployees/{id}")
    public ResponseEntity<List<EmployeeMatchDTO>> getEligibleEmployees(@PathVariable Long id){
        List<EmployeeMatchDTO> eligibleEmployees = roleRequestService.getEligibleEmployees(id);
        return ResponseEntity.ok(eligibleEmployees);
    }

    @PutMapping("/assignEmployee")
    public ResponseEntity<String> assignEmployeeToProject(@RequestParam Long id, @RequestParam Long empId){
        try{
            roleRequestService.assignEmployeeToProject(id, empId);
            return ResponseEntity.ok("Employee assigned and status updated successfully.");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Assignment failed: " + e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/getRequestsByManager")
    public ResponseEntity<List<RoleRequestDTO>> getRequestsByManager(@RequestParam Long managerEmpId){
        List<RoleRequestDTO> requests = roleRequestService.getAllRequestsByManager(managerEmpId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


}
