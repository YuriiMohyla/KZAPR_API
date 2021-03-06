package com.example.demo.repository;

import com.example.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query(value="select * from public.status a where a.status = :type", nativeQuery=true)
    List<Status> getStatusByStatus(String type);

    @Query(value="select s.* from public.status s, public.task t where t.status_id=s.status_id AND t.task_id = :id", nativeQuery=true)
    Status getStatusByTaskId(Long id);

}

