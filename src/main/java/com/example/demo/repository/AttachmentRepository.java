package com.example.demo.repository;

import com.example.demo.model.Attachment;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query(value="select a.* from public.attachment a, public.attachment_list al, public.task t where t.task_id=al.task_id AND al.attachment_id=a.attachment_id AND t.task_id = :id", nativeQuery=true)
    List<Attachment> getUserByTaskId(Long id);

}
