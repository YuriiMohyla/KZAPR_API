package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@Entity
@Table(name = "task", schema = "public")
public class Task {

    @Id
    @GeneratedValue
    private Long task_id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private Timestamp time_start;

    private Timestamp time_end;

    private String color;

    @OneToOne
    @JoinColumn(referencedColumnName = "task_id",name = "parent_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
