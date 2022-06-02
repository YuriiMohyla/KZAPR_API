package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "project_staff")
public class ProjectStaff {

    @Id
    @GeneratedValue
    private Long projectstaff_id;

}
