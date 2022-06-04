package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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

    private String name;

    private String color;

    private String status;

    @OneToMany(mappedBy = "status")
    private List<Contract> contracts;
}
