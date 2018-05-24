package com.example.wordcup.team.application.impl.service;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.domain.model.TeamRepository;
import com.example.wordcup.team.domain.model.TeamValidator;
import com.example.wordcup.team.infra.TeamValidationException;
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
    public Optional<Team> updateTeam(Team team) {
        /*if (team.getName().isEmpty())
            throw new TeamValidationException("Time não pode ser vazio");*/

        teamValidator.checkRules(team);

        return Optional.ofNullable(teamRepository.save(team));
    }

    @Override
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }
}
