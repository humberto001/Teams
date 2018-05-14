package com.example.wordcup.team.view;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.application.impl.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;


@RestController
public class TeamEndpoint {

    @Autowired
    TeamService teamService;

    @PostMapping(path = "/team", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTeam(@RequestBody Team team){
        teamService.save(team);
    }

    @GetMapping(path = "/team/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Team findTeam(@PathVariable String name){
        return teamService.findBy(name);
    }

    @GetMapping(path= "/team/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Team> findAllTeam(){
        return teamService.findAll();
    }

    @PutMapping(path = "/team/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTeam(@RequestBody Team team, @PathVariable String name){
        teamService.updateTeam(team);
    }

    @DeleteMapping(path = "team/{name}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteTeam(@PathVariable String name ){
        teamService.delete(name);

    }

}
