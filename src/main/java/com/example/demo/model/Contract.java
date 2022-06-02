package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue
    private Integer contract_id;

    @NotBlank
    private String title;

    @NotBlank
    private Timestamp created_at;

    @NotBlank
    private Timestamp time_start;

    @NotBlank
    private Timestamp time_end;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile customer;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile companyOwner;

}