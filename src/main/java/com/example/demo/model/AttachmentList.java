package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "attachment_list")
public class AttachmentList {

    @Id
    @GeneratedValue
    private Long attachment_list_id;

    @ManyToOne
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;
}
