package com.example.wordcup.team.application.impl.service;

import com.example.wordcup.team.domain.model.Team;

import java.util.Optional;

public interface TeamService {

    Team save(Team team);

    Optional <Team> findBy(Long id);

    Iterable <Team> findAll();

    Optional<Team> updateTeam(Team team);

    void delete(Long id);
}
