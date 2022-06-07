package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notification_list")
public class NotificationList {

    @Id
    @GeneratedValue
    private Long notification_list_id;

    @NotBlank
    private boolean read;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Profile profile;

}
