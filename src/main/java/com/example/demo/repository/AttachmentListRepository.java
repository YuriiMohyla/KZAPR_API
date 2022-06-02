package com.example.demo.repository;

import com.example.demo.model.AttachmentList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentListRepository extends JpaRepository<AttachmentList, Long> {
}
