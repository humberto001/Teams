package com.example.wordcup.team.domain.model;

import java.util.Optional;


public interface TeamRepository {

    Optional<Team> findBy (Long id);

    void deleteById(Long id);

    void save(Team team);

    Iterable<Team> findAll();
}
