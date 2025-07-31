package com.example.FinalProjectAllocation.repositories;

import com.example.FinalProjectAllocation.entities.RoleRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRequestRepository extends JpaRepository<RoleRequestEntity, Long> {

    List<RoleRequestEntity> findByStatus(String status);

    Long countByStatus(String status);

    List<RoleRequestEntity> findByManagerEmpId(Long managerEmpId);

}
