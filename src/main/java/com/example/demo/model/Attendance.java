package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Laboratory laboratory;

}
