package com.example.sportevents.repository.rowMapper;

import com.example.sportevents.entity.TeamEntity;
import com.example.sportevents.model.Team;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TeamRowMapper implements RowMapper<TeamEntity> {
    @Override
    public TeamEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        var team = new TeamEntity();
        team.Id = rs.getInt("id");
        team.Name = rs.getString("name");

        return team;
    }
}
