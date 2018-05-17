package com.example.wordcup.team.infraestructure.repository;

import com.example.wordcup.team.infraestructure.repository.entities.TeamEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepositorySpringData extends CrudRepository<TeamEntity,Long> {

}
