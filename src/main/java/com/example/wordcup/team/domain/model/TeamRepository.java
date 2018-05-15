package com.example.wordcup.team.domain.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team,Long> {

    //Team findBy (String name);
    //void delete(String name);
    /*void save(Team team);
    List<Team> findAll();
    void updateTeam(Team team);
    */
}
