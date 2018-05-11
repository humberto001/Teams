package com.example.wordcup.team.view;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.impl.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


@RestController
public class TeamEndpoint {

    @Autowired
    private TeamService teamService;

    @PostMapping(path = "/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTeam(@RequestBody Team team){
        teamService.save(team);
    }

    @GetMapping(path = "/team/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Team findTeam(@PathVariable String name){
        Team team = teamService.findBy(name);
        return team;
    }
}
