package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "attachment")
public class Attachment {

    @Id
    @GeneratedValue
    private Long attachment_id;

    @NotBlank
    private String link;

    @NotBlank
    private String type;

    @Override
    public String toString() {
        return "Attachment{" +
                "attachment_id=" + attachment_id +
                ", link='" + link + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
