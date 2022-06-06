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
    @Column(name = "projectstaff_id")
    private Long id;

    @OneToOne
    @JoinColumn(referencedColumnName = "projectstaff_id", name = "chief_id")
    private ProjectStaff projectStaff;
}
