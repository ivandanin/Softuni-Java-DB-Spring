package com.example.springadvanced.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @JoinTable(
            joinColumns = @JoinColumn(name = "shampoo_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id",
            referencedColumnName = "id")
    )
    private Set<Ingredient> ingredients;

}
