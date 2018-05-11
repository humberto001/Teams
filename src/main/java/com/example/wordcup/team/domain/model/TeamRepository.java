package com.example.wordcup.team.domain.model;

public interface TeamRepository {

    void save(Team team);
    Team findBy (String name);
}
