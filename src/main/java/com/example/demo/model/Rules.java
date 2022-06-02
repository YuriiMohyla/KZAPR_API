package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rules {

    @Id
    @GeneratedValue
    private Long rule_id;


}
