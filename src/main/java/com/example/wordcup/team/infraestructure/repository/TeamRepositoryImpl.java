package com.example.wordcup.team.infraestructure.repository;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.domain.model.TeamRepository;
import com.example.wordcup.team.infraestructure.repository.converters.TeamToTeamEntityConverter;
import com.example.wordcup.team.infraestructure.repository.entities.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    @Autowired
    private TeamRepositorySpringData repositorySpringData;

    @Autowired
    private TeamToTeamEntityConverter entityConverter;


    @Override
    public Optional <Team> findBy(Long id) {
        Optional <TeamEntity> entity = repositorySpringData.findById(id);
        return Optional.of(entityConverter.toModel(entity));
    }

    @Override
    public void deleteById(Long id) {
        repositorySpringData.deleteById(id);
    }

    @Override
    public void save(Team team) {
        TeamEntity entity = entityConverter.fromModel(team);
        repositorySpringData.save(entity);
    }

    @Override
    public Iterable <Team> findAll() {
        Iterable <TeamEntity> teamEntities = repositorySpringData.findAll();
        return entityConverter.toModel(teamEntities);
    }
}
