package com.example.wordcup.team.impl.service;

import com.example.wordcup.team.domain.model.Team;

import java.util.List;

public interface TeamService {

    void save(Team team);

    Team findBy(String name);

    List<Team> findAll();

    void updateTeam(Team team);

    void delete(String name);
}
