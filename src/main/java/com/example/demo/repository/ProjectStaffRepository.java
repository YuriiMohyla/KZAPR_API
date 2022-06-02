package com.example.demo.repository;

import com.example.demo.model.ProjectStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectStaffRepository extends JpaRepository<ProjectStaff, Long> {
}
