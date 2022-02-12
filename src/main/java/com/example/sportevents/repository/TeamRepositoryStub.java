package com.example.sportevents.repository;

import com.example.sportevents.entity.TeamEntity;
import com.example.sportevents.repository.abstracts.AbstractRepositoryStub;
import com.example.sportevents.repository.abstracts.TeamRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepositoryStub extends AbstractRepositoryStub<TeamEntity> implements TeamRepositoryInterface {
    @Override
    public List<TeamEntity> FindByName(String name) {
        return entities.stream().filter(e -> e.Name.startsWith(name)).toList();
    }
}
