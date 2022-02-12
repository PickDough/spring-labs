package com.example.sportevents.mapper;

import com.example.sportevents.entity.TeamEntity;
import com.example.sportevents.model.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements MapperInterface<Team, TeamEntity> {
    @Override
    public Team ToModel(TeamEntity teamEntity) {
        return new Team(teamEntity.Id, teamEntity.Name);
    }

    @Override
    public TeamEntity ToEntity(Team team) {
        var te = new TeamEntity();
        te.Id = team.getId();
        te.Name = team.getName();

        return te;
    }
}
