package com.example.springdataquering.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private Set<Ingridient> ingridients;

}
