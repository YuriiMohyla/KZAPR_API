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

    @Id                     //Primary Key
    @Column(name = "user_id")
    private Long id;

    @NotBlank               //@NotBlank должно быть не нулевым , а обрезанная длина должна быть больше нуля
    private String email;

    @NotBlank
    private String password;

    private Timestamp created_at;

    private Boolean verify;

    private Boolean google;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Profile> profileList;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Notification> notificationList;
}
