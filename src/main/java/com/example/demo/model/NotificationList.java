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
@Table(name = "notification_list")
public class NotificationList {

    @Id
    @GeneratedValue
    private Long notification_list_id;

    @NotBlank
    private boolean read;

}
