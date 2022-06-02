package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue
    private Long permission_id;

    @NotBlank
    private String name;

    private String description;

    @OneToMany
    @JoinColumn(name = "permission_id")
    private List<Rules> rulesList;

}
