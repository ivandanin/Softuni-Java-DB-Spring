package com.example.football.models.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "stats")
public class Stat {

    private Integer id;
    private Float shooting;
    private Float passing;
    private Float endurance;

    public Stat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getShooting() {
        return shooting;
    }

    public void setShooting(Float shooting) {
        this.shooting = shooting;
    }

    public Float getPassing() {
        return passing;
    }

    public void setPassing(Float passing) {
        this.passing = passing;
    }

    public Float getEndurance() {
        return endurance;
    }

    public void setEndurance(Float endurance) {
        this.endurance = endurance;
    }
}
