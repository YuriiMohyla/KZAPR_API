package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "resource")
public class Resource {
    @Id
    @GeneratedValue
    private Long resource_id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @OneToMany
    @JoinColumn(name = "resource_id")
    private List<Rules> rulesList;

}
