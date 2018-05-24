package com.example.wordcup.team.infraestructure.repository.converters;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.infra.TeamValidationException;
import com.example.wordcup.team.view.resources.TeamResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class TeamToTeamResourceConverter {

    public Team toModel(TeamResource teamResource) {
        if (isNull(teamResource))
            return null;

        Team model = new Team();
        model.setNumberOfPlayers(teamResource.getNumberOfPlayers());
        model.setName(teamResource.getName());

        return model;
    }

    public TeamResource fromModel(Optional <Team> model) {
        if (isNull(model))
            return null;

        TeamResource teamResource = new TeamResource();
        teamResource.setId(model.get().getId());
        teamResource.setName(model.get().getName());
        teamResource.setNumberOfPlayers(model.get().getNumberOfPlayers());

        return teamResource;
    }

    public Iterable <TeamResource> fromModel(Iterable <Team> teams) {
        Collection <TeamResource> resources = new ArrayList();
        teams.forEach(team -> resources.add(fromModel(Optional.of(team))));

        return resources;
    }
}
