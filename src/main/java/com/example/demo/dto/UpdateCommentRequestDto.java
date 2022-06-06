package com.example.demo.dto;


import com.example.demo.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@ToString
public class UpdateCommentRequestDto {

    private String text;
    private Timestamp dateTimeEdit ;


    public Comment toComment() {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setEdited_at(dateTimeEdit);
        return comment;
    }

}

/*
*{
    "text" : "f",
    "dateTimeEdit" : 100000000,
}*/
