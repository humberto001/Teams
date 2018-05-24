package com.example.wordcup.team.view;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.application.impl.service.TeamService;
import com.example.wordcup.team.infra.FindTeamValidationException;
import com.example.wordcup.team.infraestructure.repository.converters.TeamToTeamResourceConverter;
import com.example.wordcup.team.view.resources.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.Optional;

@RestController
public class TeamEndpoint {

    @Autowired
    private TeamToTeamResourceConverter converter;

    @Autowired
    private TeamService teamService;

    @PostMapping(path = "/team", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TeamResource addTeam(@RequestBody TeamResource resource) {
        Team team = converter.toModel(resource);
        Team teamSaved = teamService.save(team);

        return converter.fromModel(Optional.of(teamSaved));
    }

    @GetMapping(path = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamResource findTeam(@PathVariable Long id) {

        Optional<Team> team = teamService.findBy(id);

         if (!team.isPresent())
             throw new FindTeamValidationException();

         return converter.fromModel(team);
    }

    @GetMapping(path = "/team/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <TeamResource> findAllTeam() {
        Iterable <TeamResource> resources = converter.fromModel(teamService.findAll());
        return resources;
    }

    @PutMapping(path = "/team/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeamResource updateTeam(@RequestBody TeamResource resource, @PathVariable Long id) {

        Team team = converter.toModel(resource);
        team.setId(id);
        Optional <Team> updatedTeam = teamService.updateTeam(team);

        return new TeamResource(converter.fromModel(updatedTeam));
    }

    @DeleteMapping(path = "team/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTeam(@PathVariable Long id) {
        teamService.delete(id);
    }

}
