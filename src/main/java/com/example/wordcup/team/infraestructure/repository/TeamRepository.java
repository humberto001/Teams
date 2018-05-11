package com.example.wordcup.team.infraestructure.repository;

import com.example.wordcup.team.domain.model.Team;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeamRepository implements com.example.wordcup.team.domain.model.TeamRepository {

    List teams = new ArrayList<Team>();

    @Override
    public void save(Team team) {
        teams.add(team);
    }

   @Override
    public Team findBy(String name) {
        Team team = new Team(name);
        return team;
        //fazer a comparação por nome
    }

}
