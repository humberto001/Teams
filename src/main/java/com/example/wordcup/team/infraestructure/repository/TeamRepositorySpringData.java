package com.example.wordcup.team.infraestructure.repository;

import com.example.wordcup.team.infraestructure.repository.entities.TeamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamRepositorySpringData extends CrudRepository<TeamEntity,Long> {

    @Query(value = "SELECT * FROM TEAMS WHERE NAME = ?1", nativeQuery = true)
    Optional<TeamEntity> findBy(String name);
}
