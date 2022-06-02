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
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue
    private Long project_id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private Timestamp created_at;

    @NotBlank
    private Timestamp planned_at;

    @NotBlank
    private String image;

    @OneToMany(mappedBy = "project")
    private List<Task> taskList;

}