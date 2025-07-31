package com.example.FinalProjectAllocation.repositories;

import com.example.FinalProjectAllocation.entities.EmployeeEntity;
import com.example.FinalProjectAllocation.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByProjectEntity_proId(Long proid);
    List<EmployeeEntity> findByDesignation(String designation);

    @Query(value = "SELECT * FROM employees WHERE available = true AND designation = :designation", nativeQuery = true)
    List<EmployeeEntity> findAvailableEmployeesByDesignation(String designation);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.skills = :requiredSkills")
    List<EmployeeEntity> findEmployeesWithExactSkills(@Param("requiredSkills") String requiredSkills);

    //@Query("SELECT e FROM EmployeeEntity e WHERE e.skills LIKE %:requiredSkills%")
    //List<EmployeeEntity> findEmployeesWithMPartialSkills(@Param("requiredSkills") String requiredSkills);


    List<EmployeeEntity> findByProjectEntity(ProjectEntity project);

    EmployeeEntity findByEmpId(Long empId);

    List<EmployeeEntity> findByAvailableTrue();

    List<EmployeeEntity> findByProjectEntityIsNull();
}

