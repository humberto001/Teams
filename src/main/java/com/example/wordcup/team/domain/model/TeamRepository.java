package com.example.wordcup.team.domain.model;

import java.util.List;

public interface TeamRepository {

    void save(Team team);

    Team findBy (String name);

    List<Team> findAll();

    void updateTeam(Team team);

    void delete(String name);
}
