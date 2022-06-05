package com.example.demo.dto;


import com.example.demo.model.Comment;
import com.example.demo.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

/*
*Text
DatetimeAdd
type (COMMENT!!!!) */

@AllArgsConstructor
@Getter
@ToString
public class CommentRequestDto {

    private String text;
    private Timestamp datatimeAdd;
    private String type;

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setCreated_at(datatimeAdd);

        comment.setType(type);
        return comment;
    }

}

/*{
    "text" : "f",
    "startTime" : 100000000,
    "type" : "comment"
}*/
