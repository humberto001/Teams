package com.example.wordcup.team.application.impl.service;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.domain.model.TeamRepository;
import com.example.wordcup.team.domain.model.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamValidator teamValidator;

    @Override
    public void save(Team team) {
        teamValidator.checkRules(team);
        teamRepository.save(team);
    }

    @Override
    public Optional <Team> findBy(Long id) {
        return teamRepository.findBy(id);
    }

    @Override
    public Iterable <Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public void updateTeam(Team team) {
        teamValidator.checkRules(team);
        teamRepository.save(team);
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
