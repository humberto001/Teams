package com.example.wordcup.team.impl.service;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.domain.model.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void save(Team team) {
        teamRepository.save(team);
    }

    @Override
    public Team findBy(String name) {
       return teamRepository.findBy(name);
    }
}