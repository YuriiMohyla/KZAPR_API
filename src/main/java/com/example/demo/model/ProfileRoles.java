package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profile_roles")
public class ProfileRoles {

    @Id
    @GeneratedValue
    private Long profile_role_id;

    @NotBlank
    private int role_id;

}
