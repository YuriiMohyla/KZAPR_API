package com.example.demo.dto;


import com.example.demo.model.Comment;
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
public class NewCommentRequestDto {

    private String text;
    private Timestamp datatimeAdd;
    private String type;
    private Long author_id;
    private Long task_id;

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
    "datatimeAdd" : 100000000,
    "type" : "comment",
    "author_id" : 1,
    "task_id" : 1
}*/
