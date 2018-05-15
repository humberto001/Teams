package com.example.wordcup.team.infraestructure.repository;

import com.example.wordcup.team.domain.model.Team;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TeamRepository  {

   /* List<Team> teams = new ArrayList<>();

    @Override
    public void save(Team team) {
        teams.add(team);
    }

   @Override

    public Team findBy(String name) {

      for (Team team : teams){
           if (team.getName().equals(name))
               return team;
       }
      return null;
    }

    @Override
    public List <Team> findAll() {
        return teams;
    }

    @Override
    public void updateTeam(Team team) {
        for (Team t : teams){
            if (t.getName().equals(team.getName())){
                t.setNumberOfPlayers(team.getNumberOfPlayers());
            }
        }
    }

    @Override
    public void delete(String name) {
        for (Team team : teams){
            if (team.getName().equals(name)){
                teams.remove(team);
            }
        }
    }*/
}
