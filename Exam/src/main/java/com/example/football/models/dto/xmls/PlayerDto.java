package com.example.football.models.dto.xmls;

import com.example.football.config.LocalDateAdapter;
import com.example.football.models.entity.Position;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDto {

    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement(name = "emal")
    private String email;
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;
    @XmlElement(name = "position")
    private Position position;
    @XmlElement(name = "town")
    private TownXMLDto town;
    @XmlElement(name = "team")
    private TeamXMLDto team;
    @XmlElement(name = "stat")
    private StatXMLDto stat;

    public PlayerDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]+")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TownXMLDto getTown() {
        return town;
    }

    public void setTown(TownXMLDto town) {
        this.town = town;
    }

    public StatXMLDto getStat() {
        return stat;
    }

    public void setStat(StatXMLDto stat) {
        this.stat = stat;
    }

    public TeamXMLDto getTeam() {
        return team;
    }

    public void setTeam(TeamXMLDto team) {
        this.team = team;
    }
}
