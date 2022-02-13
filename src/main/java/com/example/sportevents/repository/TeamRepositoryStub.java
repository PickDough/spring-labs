package com.example.sportevents.repository;

import com.example.sportevents.entity.TeamEntity;
import com.example.sportevents.repository.abstracts.AbstractRepositoryStub;
import com.example.sportevents.repository.abstracts.TeamRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepositoryStub extends AbstractRepositoryStub<TeamEntity> implements TeamRepositoryInterface {
    public TeamRepositoryStub() {
        var t1 = new TeamEntity();
        t1.Name = "OG";

        Create(t1);

        var t2 = new TeamEntity();
        t2.Name = "Liquid";

        Create(t2);

        var t3 = new TeamEntity();
        t3.Name = "4-A";

        Create(t3);

        var t4 = new TeamEntity();
        t4.Name = "5-Б";

        Create(t4);
    }

    @Override
    public List<TeamEntity> FindByName(String name) {
        return entities.stream()
                .filter(e -> {
                    if (name.isEmpty())
                        return true;
                    return e.Name.toLowerCase().contains(name.toLowerCase());
                })
                .toList();
    }

    @Override
    public List<TeamEntity> FindByNameAndPagination(String name, int limit, int offset) {
        return entities.stream()
                .filter(e -> {
                    if (name.isEmpty())
                        return true;
                    return e.Name.toLowerCase().contains(name.toLowerCase());
                })
                .skip(offset)
                .limit(limit == 0 ? Integer.MAX_VALUE : limit)
                .toList();
    }
}
