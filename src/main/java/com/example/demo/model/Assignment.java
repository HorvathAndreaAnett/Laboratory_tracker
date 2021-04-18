package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Date deadline;
    private String description;

    @OneToOne
    private Laboratory laboratory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignment")
    private Set<Submission> submissionSet;

}
