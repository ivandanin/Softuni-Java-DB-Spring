package com.example.springadvanced.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "labels")
@Data
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subtitle;

    @OneToMany(mappedBy = "label")
    private Set<Shampoo> shampoos = new HashSet<>();
}
