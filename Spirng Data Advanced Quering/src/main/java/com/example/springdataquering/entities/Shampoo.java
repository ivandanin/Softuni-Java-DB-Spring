package com.example.springdataquering.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shampoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    private BigDecimal price;

    @Enumerated(EnumType.ORDINAL)
    private Size size;

    @ManyToOne
    private Label label;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();

}
