package com.example.wordcup.team.infraestructure.repository;

import com.example.wordcup.team.domain.model.Team;
import com.example.wordcup.team.domain.model.TeamRepository;
import com.example.wordcup.team.infraestructure.repository.converters.TeamToTeamEntityConverter;
import com.example.wordcup.team.infraestructure.repository.entities.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    @Autowired
    private TeamRepositorySpringData repositorySpringData;

    @Autowired
    private TeamToTeamEntityConverter entityConverter;


    @Override
    public Optional <Team> findBy(Long id) {

        Optional <TeamEntity> entity = repositorySpringData.findById(id);
        return Optional.ofNullable(entityConverter.toModel(entity));
    }

    @Override
    public Optional <Team> findBy(String name) {

        Optional<TeamEntity> entity = repositorySpringData.findBy(name);
        return Optional.ofNullable(entityConverter.toModel(entity));
    }

    @Override
    public void deleteById(Long id) {
        repositorySpringData.deleteById(id);
    }

    @Override
    public Team save(Team team) {
        TeamEntity entity = entityConverter.fromModel(team);
        repositorySpringData.save(entity);
        team.setId(entity.getId());
        return team;
    }

    @Override
    public Iterable <Team> findAll() {
        Iterable <TeamEntity> teamEntities = repositorySpringData.findAll();
        return entityConverter.toModel(teamEntities);
    }
}
