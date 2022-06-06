package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    private Long comment_id;


    private String text;


    private Timestamp created_at;


    private Timestamp edited_at;


    private String type;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

}
