package com.example.wordcup.team.application.impl.service;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.domain.model.TeamRepository;
import com.example.wordcup.team.domain.model.TeamValidator;
import com.example.wordcup.team.infra.exceptions.FindTeamValidationException;
import com.example.wordcup.team.infra.exceptions.TeamValidationException;
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
    public Team save(Team team) {

        if (teamRepository.findBy(team.getName()).isPresent())
            throw new TeamValidationException("Time já existente");

        teamValidator.checkRules(team);
        return teamRepository.save(team);
    }

    @Override
    public Optional <Team> findBy(Long id) {

        Optional <Team> team = teamRepository.findBy(id);
        return team;
    }

    @Override
    public Iterable <Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional <Team> updateTeam(Team team) {
        if (!teamRepository.findBy(team.getId()).isPresent()) {
            throw new FindTeamValidationException();
        }

        teamValidator.checkRules(team);
        return Optional.ofNullable(teamRepository.save(team));
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
