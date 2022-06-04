package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue
    private Long contract_id;

    private String title;

    private Timestamp time_start;

    private Timestamp time_end;

    private String description;

    private String image;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Profile customer;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Profile owner;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "contract")
    private List<Project> projects;
}