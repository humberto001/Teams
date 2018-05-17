package com.example.wordcup.team.infraestructure.repository.converters;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.infraestructure.repository.entities.TeamEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class TeamToTeamEntityConverter {

    public Team toModel(Optional <TeamEntity> entity){

        Team model = new Team();
        model.setId(entity.get().getId());
        model.setName(entity.get().getName());
        model.setNumberOfPlayers(entity.get().getNumberOfPlayers());

        return  model;
    }

    public TeamEntity fromModel(Team model){

        TeamEntity entity = new TeamEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setNumberOfPlayers(model.getNumberOfPlayers());

        return entity;
    }

    public Iterable <Team> toModel(Iterable<TeamEntity> teamEntities) { //Pra cada entidade de teamEntities, converter para o model e adicionar em teams
        Collection <Team> teams = new ArrayList((Collection) teamEntities);

        return teams;
    }
}
