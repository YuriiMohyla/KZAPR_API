package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue
    private Long profile_id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private String avatar;

    @NotBlank
    private Date birthday;

}
