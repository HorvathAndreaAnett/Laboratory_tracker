package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data

public class Grupa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer nr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupa")
    private Set<User> userSet;

}
