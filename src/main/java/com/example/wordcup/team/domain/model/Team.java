package com.example.wordcup.team.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="team")
public class Team {

    private String name;
    private int numberOfPlayers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

}