package com.example.wordcup.team.application.impl.service;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.domain.model.TeamRepository;
import com.example.wordcup.team.domain.model.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Team findBy(String name) {
       return teamRepository.findBy(name);
    }

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public void updateTeam(Team team) {
        teamValidator.checkRules(team);
        teamRepository.updateTeam(team);
    }

    @Override
    public void delete(String name) {
        teamRepository.delete(name);
    }

}
