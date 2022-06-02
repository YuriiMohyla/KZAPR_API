package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue
    private Long role_id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @OneToMany
    @JoinColumn(name = "role_id")
    private List<Rules> rulesList;

}
