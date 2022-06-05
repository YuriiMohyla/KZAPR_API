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
    private List<Task> task;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + project_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", planned_at=" + planned_at +
                ", image='" + image + '\'' +
                '}';
    }
}