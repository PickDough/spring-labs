package com.example.sportevents.repository.rowMapper;

import com.example.sportevents.entity.EventEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EventRowMapper implements RowMapper<EventEntity> {
    @Override
    public EventEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        var event = new EventEntity();
        event.Id = rs.getInt("id");
        event.Title = rs.getString("title");
        event.FirstTeamId = rs.getInt("id_first_team");
        event.SecondTeamId = rs.getInt("id_second_team");
        event.SecondTeamId = rs.getInt("id_second_team");
        event.Date = Date.valueOf(rs.getString("date"));

        return event;
    }
}
