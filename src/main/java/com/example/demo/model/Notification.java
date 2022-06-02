package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue
    private Long notification_id;

    @NotBlank
    private String text;

    @NotBlank
    private Timestamp created_at;

    @NotBlank
    private Timestamp planned_at;

    @NotBlank
    private char priority;

/*    @OneToMany
    @JoinColumn(name = "permission_id")
    private List<Rules> rulesList;*/

}
