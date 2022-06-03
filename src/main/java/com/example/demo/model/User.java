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
@Table(name = "user", schema = "public")
public class User {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    private Timestamp created_at;

    private Boolean verify;

    private Boolean google;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Profile profile;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Notification> notificationList;
}
