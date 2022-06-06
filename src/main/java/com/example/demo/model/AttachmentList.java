package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attachment_list")
public class AttachmentList {

    @Id
    @GeneratedValue
    private Long attachment_list_id;

    @ManyToOne
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
