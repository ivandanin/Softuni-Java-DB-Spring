package com.example.football.models.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "teams")
public class Team {

    private Integer id;
    private String name;
    private String stadiumName;
    private BigInteger fanBase;
    private String history;
    private Town town;

    public Team() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Length(min = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "stadium_name")
    @Length(min = 3)
    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Column(name = "fan_base")
    public BigInteger getFanBase() {
        return fanBase;
    }

    public void setFanBase(BigInteger fanBase) {
        this.fanBase = fanBase;
    }

    @Length(min = 10)
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
