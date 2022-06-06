package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProfileRepository profileRepository;

    @PostMapping("/comment/{commentId}")
    public ResponseDto createComment(@RequestBody NewCommentRequestDto newCommentRequestDto, @PathVariable(value = "commentId") Long comment_id) {
        Comment comment = newCommentRequestDto.toComment();
        comment.setComment_id(comment_id);
        if (newCommentRequestDto.getAuthor_id() != null)
            profileRepository.findById(newCommentRequestDto.getAuthor_id()).ifPresent(comment::setProfile);
        if (newCommentRequestDto.getTask_id() != null)
            taskRepository.findById(newCommentRequestDto.getTask_id()).ifPresent(comment::setTask);
        commentRepository.save(comment);
        return new ResponseDto("new comment", true, null);
    }

    @PutMapping("/comment/{commentId}")
    public ResponseDto updateComment(@RequestBody UpdateCommentRequestDto updateCommentRequestDto, @PathVariable(value = "commentId") Long comment_id) {
        try {
            Comment comment = commentRepository.findById(comment_id).get();
            comment.setText(updateCommentRequestDto.getText());
            comment.setEdited_at(updateCommentRequestDto.getDateTimeEdit());
            commentRepository.save(comment);
            return new ResponseDto("update comment", true, null);
        } catch (RuntimeException e){
            return new ResponseDto("comment not found by id", false, null);
        }
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseDto deleteComment(@PathVariable(value = "commentId") Long commentId){
        try {
            commentRepository.deleteById(commentId);
            return new ResponseDto("delete comment", true, null);
        } catch (RuntimeException e){
            return new ResponseDto("comment not found by id", false, null);
        }
    }

}
