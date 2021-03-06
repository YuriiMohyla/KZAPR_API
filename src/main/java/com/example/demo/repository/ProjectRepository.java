package com.example.demo.repository;

import com.example.demo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value="select * from public.project a where a.contract_id = :id", nativeQuery=true)
    List<Project> getProjectsByContract(Long id);

    @Query(value="select pj.* from public.project pj, public.task t where pj.project_id=t.project_id AND t.task_id = :id", nativeQuery=true)
    Project getProjectsByTaskId(Long id);
}
