package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Assignment assignment;

    private String link;
    private String comment;
    private Float grade;

}
