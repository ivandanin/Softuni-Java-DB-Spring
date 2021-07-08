package com.example.springdataadvquering.entities;


import javax.persistence.*;
import java.util.Set;

@Entity

@Table(name = "shampoos")
public class Shampoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private double price;

    @Enumerated(EnumType.ORDINAL)
    private Size size;

    @ManyToOne
    private Label label;

    @ManyToMany
    private Set<Ingredient> ingredients;
}
