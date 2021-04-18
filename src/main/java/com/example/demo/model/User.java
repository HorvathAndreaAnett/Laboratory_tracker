package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Role role;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String token;
    private String emailAddr;

    @ManyToOne
    private Grupa grupa;

    private String hobby;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Attendance> attendanceSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Submission> submissionSet;

    public String getFullName() {
        return lastName + " " + firstName;
    }


}
