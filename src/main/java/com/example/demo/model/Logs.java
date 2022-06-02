package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "logs")
public class Logs {
    @Id
    @GeneratedValue
    private Integer logs_id;

    @NotBlank
    private String text;

    @NotBlank
    private Timestamp created_at;

}