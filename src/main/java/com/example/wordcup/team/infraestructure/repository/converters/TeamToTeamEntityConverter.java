package com.example.wordcup.team.infraestructure.repository.converters;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.infraestructure.repository.entities.TeamEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class TeamToTeamEntityConverter {

    public Team toModel(Optional <TeamEntity> optionalEntity){

        if (!optionalEntity.isPresent()){return null;}

        Team model = new Team();
        model.setId(optionalEntity.get().getId());
        model.setName(optionalEntity.get().getName());
        model.setNumberOfPlayers(optionalEntity.get().getNumberOfPlayers());

        return  model;
    }

    public TeamEntity fromModel(Team model){

        TeamEntity entity = new TeamEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setNumberOfPlayers(model.getNumberOfPlayers());

        return entity;
    }

    public Iterable <Team> toModel(Iterable<TeamEntity> teamEntities) {
        Collection <Team> teams = new ArrayList();
        teamEntities.forEach(entity -> teams.add(toModel(Optional.of(entity))));

        return teams;
    }
}
