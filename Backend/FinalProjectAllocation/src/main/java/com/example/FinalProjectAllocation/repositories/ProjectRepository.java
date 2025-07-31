package com.example.FinalProjectAllocation.repositories;

import com.example.FinalProjectAllocation.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    List<ProjectEntity> findByEmployeesIsEmpty();

    @Query(value= "SELECT * FROM projects WHERE " + "(SELECT COUNT(*) FROM employees WHERE employees.pro_Id = projects.pro_Id) < projects.capacity",        nativeQuery = true)
    List<ProjectEntity> findUnderstaffedProjects();


    //List<ProjectEntity> findByProid(ProjectEntity projectEntity);
}
