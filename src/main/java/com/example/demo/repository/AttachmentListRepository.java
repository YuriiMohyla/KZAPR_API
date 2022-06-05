package com.example.demo.repository;

import com.example.demo.model.Attachment;
import com.example.demo.model.AttachmentList;
import com.example.demo.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentListRepository extends JpaRepository<AttachmentList, Long> {
    @Query(value="select a.* from public.attachment a,public.attachment_list s where a.attachment_id = s.attachment_id AND s.contract_id=:id", nativeQuery=true)
    List<Attachment> getAttachmentListByContractId(Long id);
}
