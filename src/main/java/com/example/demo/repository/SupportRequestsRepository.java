package com.example.demo.repository;

import com.example.demo.model.SupportRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRequestsRepository extends JpaRepository<SupportRequests, Long> {
}
