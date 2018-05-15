package com.example.wordcup.team.view;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.application.impl.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.Optional;

@RestController
public class TeamEndpoint {

    @Autowired
    TeamService teamService;

    @PostMapping(path = "/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTeam(@RequestBody Team team) {
        teamService.save(team);
    }

    @GetMapping(path = "/team/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional <Team> findTeam(@PathVariable Long id) {
        return teamService.findBy(id);
    }

    @GetMapping(path = "/team/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable <Team> findAllTeam() {
        return teamService.findAll();
    }

    @PutMapping(path = "/team/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTeam(@RequestBody Team team, @PathVariable Long id) {
        teamService.updateTeam(team);
    }

    @DeleteMapping(path = "team/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTeam(@PathVariable Long id) {
        teamService.delete(id);

    }

}
