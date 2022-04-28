package com.example.sportevents.repository;

import com.example.sportevents.entity.EventEntity;
import com.example.sportevents.entity.TeamEntity;
import com.example.sportevents.repository.abstracts.EventRepositoryInterface;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository implements EventRepositoryInterface {
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<EventEntity> rowMapper;
    private SimpleDateFormat format = new SimpleDateFormat("2022-06-12");
    private final String tableName = "events";

    public EventRepository(JdbcTemplate jdbcTemplate, RowMapper<EventEntity> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public int Create(EventEntity entity) {
        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            var stm = con.prepareStatement(
                    "INSERT INTO " + tableName + " (title, id_first_team, id_second_team, date) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stm.setString(1, entity.Title);
            stm.setInt(2, entity.FirstTeamId);
            stm.setInt(3, entity.SecondTeamId);
            stm.setString(4, format.format(entity.Date));
            return stm;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Optional<EventEntity> Read(int id) {
        var sql =
                "Select * from " + tableName + " where id = ?";

        var entity = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return entity == null ? Optional.empty() : Optional.of(entity);
    }

    @Override
    public List<EventEntity> ReadAll() {
        var sql =
                "Select * from " + tableName;

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void Update(EventEntity entity) {
        var sql =
                "Update " + tableName + " set title = ?, id_first_team = ?, id_second_team = ?, date = ?   where id = ?";

        jdbcTemplate.update(sql, entity.Title, entity.FirstTeamId, entity.SecondTeamId, entity.Date, entity.Id);
    }

    @Override
    public void Delete(int id) {
        var sql =
                "Delete from " + tableName + " where id = ?";

        jdbcTemplate.update(sql, id);
    }
}
