package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue
    private Long status_id;

    @NotBlank
    private String name;

    @NotBlank
    private String color;

    @NotBlank
    private String status_type;

    @OneToMany(mappedBy = "status")
    private List<Contract> contracts;
}
