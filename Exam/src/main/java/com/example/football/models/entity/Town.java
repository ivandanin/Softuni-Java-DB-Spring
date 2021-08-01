package com.example.football.models.entity;

import org.attoparser.dom.Text;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "towns")
public class Town {

    private Integer id;
    private String name;
    private BigInteger population;
    private String travelGuide;

    public Town() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Length(min = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPopulation() {
        return population;
    }

    public void setPopulation(BigInteger population) {
        this.population = population;
    }

    @Column(name = "travel_guide")
    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
