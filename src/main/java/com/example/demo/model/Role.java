package com.example.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

}
