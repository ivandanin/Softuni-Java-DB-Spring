package com.example.springadvanced.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

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
    @ManyToOne(optional = true)
    private Label label;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {PERSIST, REFRESH})
    @JoinTable(
            joinColumns = @JoinColumn(name="shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="ingredient_id", referencedColumnName = "id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

}
