package com.example.sportevents.repository.abstracts;

import com.example.sportevents.entity.TeamEntity;

import java.util.List;

public interface TeamRepositoryInterface extends RepositoryInterface<TeamEntity> {
    List<TeamEntity> FindByName(String name);

    List<TeamEntity> FindByNameAndPagination(String name, int limit, int offset);
}
