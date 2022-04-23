package com.example.sportevents.repository.rowMapper;

import com.example.sportevents.entity.EventResultEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EventResultRowMapper implements RowMapper<EventResultEntity> {
    @Override
    public EventResultEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        var event = new EventResultEntity();
        event.Id = rs.getInt("id");
        event.EventId = rs.getInt("id_event");
        event.FirstTeamScore = rs.getInt("first_team_score");
        event.SecondTeamScore = rs.getInt("second_team_score");
        event.WinnerId = rs.getInt("id_winner");

        return event;
    }
}
