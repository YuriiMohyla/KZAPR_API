package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "support_requests")
public class SupportRequests {

    @Id
    @GeneratedValue
    private Long support_requests_id;

    @NotBlank
    private String message;

    @NotBlank
    private boolean read;

    @NotBlank
    private Timestamp time;

}
