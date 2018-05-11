package com.example.wordcup.team.impl.service;

import com.example.wordcup.team.domain.model.Team;

public interface TeamService {

    void save(Team team);

    Team findBy(String name);
}
