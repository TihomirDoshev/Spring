package com.example.football.models.dto.xml;

import com.example.football.models.entity.Position;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDto {
    @XmlElement(name = "first-name")
    @Size(min = 2)
    private String firstName;
    @XmlElement(name = "last-name")
    @Size(min = 2)
    private String lastName;
    @XmlElement(name = "email")
    @Email
    private String email;
    @XmlElement(name = "birth-date")
    private String birthDate;
    @XmlElement(name = "position")
    private Position position;
    @XmlElement
    private PlayerTown town;
    @XmlElement
    private PlayerTeam team;
    @XmlElement
    private PlayerStat stat;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PlayerTown getTown() {
        return town;
    }

    public void setTown(PlayerTown town) {
        this.town = town;
    }

    public PlayerTeam getTeam() {
        return team;
    }

    public void setTeam(PlayerTeam team) {
        this.team = team;
    }

    public PlayerStat getStat() {
        return stat;
    }

    public void setStat(PlayerStat stat) {
        this.stat = stat;
    }
}
