package com.example.demo.repository;

import com.example.demo.dto.GetAllCommentsOfTheTaskResponseDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Contract;
import com.example.demo.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value="select c.* from public.comment c, public.profile p where c.author_id = p.profile_id AND c.task_id = :id", nativeQuery=true)
    List<Comment> getAllCommentsOfTheTaskList(Long id);



}
