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
    @GeneratedValue
    private Long comment_id;

    @NotBlank
    private String text;

    @NotBlank
    private Timestamp created_at;

    @NotBlank
    private Timestamp edited_at;

    @NotBlank
    private String type;


}
