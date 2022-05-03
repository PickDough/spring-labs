package com.example.sportevents.service;

import com.example.sportevents.entity.TeamEntity;
import com.example.sportevents.mapper.MapperInterface;
import com.example.sportevents.model.Event;
import com.example.sportevents.model.Team;
import com.example.sportevents.repository.abstracts.TeamRepositoryInterface;
import com.example.sportevents.service.abstracts.AbstractService;
import com.example.sportevents.service.abstracts.TeamServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class TeamService extends AbstractService<Team, TeamEntity> implements TeamServiceInterface {
    private final TeamRepositoryInterface repository;

    public TeamService(MapperInterface<Team, TeamEntity> mapper, TeamRepositoryInterface repositoryInterface) {
        super(mapper, repositoryInterface);
        repository = repositoryInterface;
    }

    @Override
    public List<Team> GetByName(String name) {
        return repository.FindByName(name).stream().map(mapper::ToModel).toList();
    }

    @Override
    public List<Team> GetAllWithFilterAndPagination(String name, int limit, int offset) {
        return repository.FindByNameAndPagination(name, limit, offset).stream().map(mapper::ToModel).toList();
    }

    public List<Team> FindTeamsByText(String inputString){

        ArrayList<Team> foundTeams = new ArrayList<Team>();

        for(Team t : GetAll()){

            String teamString = t.getName();
            if (teamString.matches(".*" + Pattern.quote(inputString) + ".*")){
                foundTeams.add(t);
            }
        }

        return foundTeams;

    }

}
