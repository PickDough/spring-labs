package com.example.sportevents.repository;

import com.example.sportevents.entity.TeamEntity;
import com.example.sportevents.repository.abstracts.TeamRepositoryInterface;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository implements TeamRepositoryInterface {
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<TeamEntity> rowMapper;

    private final String tableName = "teams";

    public TeamRepository(JdbcTemplate jdbcTemplate, RowMapper<TeamEntity> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public int Create(TeamEntity entity) {
        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            var stm = con.prepareStatement(
                    "INSERT INTO " + tableName + " (name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stm.setString(1, entity.Name);
            return stm;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public Optional<TeamEntity> Read(int id) {
        var sql =
                "Select * from " + tableName + " where id = ?";

        var entity = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return entity == null ? Optional.empty() : Optional.of(entity);
    }

    @Override
    public List<TeamEntity> ReadAll() {
        var sql =
                "Select * from " + tableName;

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void Update(TeamEntity entity) {
        var sql =
                "Update " + tableName + " set name = ? where id = ?";

        jdbcTemplate.update(sql, entity.Name, entity.Id);
    }

    @Override
    public void Delete(int id) {
        var sql =
                "Delete from " + tableName + " where id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<TeamEntity> FindByName(String name) {
        var sql =
                "Select * from " + tableName + " where name like '%" + name + "%'";

        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<TeamEntity> FindByNameAndPagination(String name, int limit, int offset) {
        return null;
    }
}
