package com.example.wordcup.team.view;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.application.impl.service.TeamService;
import com.example.wordcup.team.infraestructure.repository.converters.TeamToTeamResourceConverter;
import com.example.wordcup.team.view.resources.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
public class TeamEndpoint {

    @Autowired
    private TeamToTeamResourceConverter converter;

    @Autowired
    private TeamService teamService;

    @PostMapping(path = "/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTeam(@RequestBody TeamResource resource) {
        Team team = converter.toModel(resource);
        teamService.save(team);
    }

    @GetMapping(path = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeamResource findTeam(@PathVariable Long id) {
        TeamResource resource = converter.fromModel(teamService.findBy(id));
        return resource;
    }

    @GetMapping(path = "/team/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <TeamResource> findAllTeam() {
        Iterable <TeamResource> resources = converter.fromModel(teamService.findAll());
        return resources;
    }

    @PutMapping(path = "/team/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTeam(@RequestBody TeamResource resource, @PathVariable Long id) {
        Team team = converter.toModel(resource);
        team.setId(id);
        teamService.updateTeam(team);
    }

    @DeleteMapping(path = "team/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTeam(@PathVariable Long id) {
        teamService.delete(id);

    }

}
