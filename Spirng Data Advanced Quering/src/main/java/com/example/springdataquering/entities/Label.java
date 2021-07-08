package com.example.springdataquering.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Label {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String subtitle;

    @OneToMany(mappedBy = "label")
    private Set<Shampoo> shampoos;

}
