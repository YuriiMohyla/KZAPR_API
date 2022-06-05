package com.example.demo.controller;

import com.example.demo.dto.CommentRequestDto;
import com.example.demo.dto.ContractRequestDto;
import com.example.demo.dto.ListProjectsOfContractDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Contract;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/comment/{commentId}")
    public ResponseDto createNote(@RequestBody CommentRequestDto commentRequestDto, @PathVariable(value = "commentId") Long comment_id) {
        Comment comment = commentRequestDto.toComment();
        comment.setComment_id(comment_id);
        commentRepository.save(comment);
        return new ResponseDto("new comment", true, null);
    }

}
