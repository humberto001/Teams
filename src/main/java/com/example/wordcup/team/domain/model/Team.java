package com.example.wordcup.team.domain.model;


public class Team {

    private Long id;
    private String name;
    private int numberOfPlayers;

    public Team(Long id, String name, int numberOfPlayers) {
        this.id = id;
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;
    }

    public Team() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}