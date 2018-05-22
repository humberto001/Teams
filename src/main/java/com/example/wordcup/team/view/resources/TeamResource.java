package com.example.wordcup.team.view.resources;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

public class TeamResource implements Serializable {
    @JsonPropertyOrder({
            "id",
            "name",
            "numberOfPlayers"
    })

    private Long id;
    private String name;
    private int numberOfPlayers;

    public TeamResource(TeamResource resource) {
    }

    public TeamResource() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
