package com.example.football.models.dto.jsons;

import com.google.gson.annotations.Expose;
import org.attoparser.dom.Text;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.math.BigInteger;

public class TownDto {

    @Expose
    private String name;
    @Expose
    private BigInteger population;

    public TownDto() {
    }

    @Length(min = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(value = 1)
    public BigInteger getPopulation() {
        return population;
    }

    public void setPopulation(BigInteger population) {
        this.population = population;
    }

}
