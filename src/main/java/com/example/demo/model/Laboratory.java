package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data

public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private Integer nr;

    private Date date;
    private String title;
    private String curricula;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Assignment assignment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
    private Set<Attendance> attendanceSet;

}
