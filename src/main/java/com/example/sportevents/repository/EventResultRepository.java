package com.example.sportevents.repository;

import com.example.sportevents.entity.EventEntity;
import com.example.sportevents.entity.EventResultEntity;
import com.example.sportevents.repository.abstracts.EventResultRepositoryInterface;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class EventResultRepository implements EventResultRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;
    private RowMapper<EventResultEntity> rowMapper;

    private final String tableName = "event_results";

    public EventResultRepository(JdbcTemplate jdbcTemplate, RowMapper<EventResultEntity> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public int Create(EventResultEntity entity) {
        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            var stm = con.prepareStatement(
                    "INSERT INTO " + tableName + " (id_event, first_team_score, second_team_score, id_winner) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stm.setInt(1, entity.EventId);
            stm.setInt(2, entity.FirstTeamScore);
            stm.setInt(3, entity.SecondTeamScore);
            stm.setInt(4, entity.WinnerId);
            return stm;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Optional<EventResultEntity> Read(int id) {
        var sql =
                "Select * from " + tableName + " where id = ?";

        var entity = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return entity == null ? Optional.empty() : Optional.of(entity);
    }

    @Override
    public List<EventResultEntity> ReadAll() {
        var sql =
                "Select * from " + tableName;

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void Update(EventResultEntity entity) {
        var sql =
                "Update " + tableName + " set id_event = ?, first_team_score = ?, second_team_score = ?, id_winner = ?   where id = ?";

        jdbcTemplate.update(sql, entity.EventId, entity.FirstTeamScore, entity.SecondTeamScore, entity.WinnerId, entity.Id);
    }

    @Override
    public void Delete(int id) {
        var sql =
                "Delete from " + tableName + " where id = ?";

        jdbcTemplate.update(sql, id);
    }
}
