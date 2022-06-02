package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "task_list")
public class TaskList {

    @Id
    @GeneratedValue
    private Long task_list_id;

    @NotBlank
    private String type;

}
