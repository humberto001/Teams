package com.example.wordcup.team.domain.model;

import java.util.Optional;


public interface TeamRepository {

    Optional<Team> findBy (Long id);

    Optional<Team> findBy(String name);

    void deleteById(Long id);

    Team save(Team team);

    Iterable<Team> findAll();
}
