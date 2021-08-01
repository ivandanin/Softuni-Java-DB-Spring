package com.example.football.models.dto.jsons;

import com.example.football.models.entity.Town;
import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class TeamDto {

    @Expose
    private String name;
    @Expose
    private String stadiumName;
    @Expose
    private BigInteger fanBase;
    @Expose
    private String townName;

    public TeamDto() {
    }

    @Length(min = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3)
    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Min(value = 1000)
    public BigInteger getFanBase() {
        return fanBase;
    }

    public void setFanBase(BigInteger fanBase) {
        this.fanBase = fanBase;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
