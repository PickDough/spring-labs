package com.example.sportevents.repository;

import com.example.sportevents.entity.EventEntity;
import com.example.sportevents.repository.abstracts.AbstractRepositoryStub;
import com.example.sportevents.repository.abstracts.EventRepositoryInterface;
import com.example.sportevents.repository.abstracts.RepositoryInterface;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;

@Repository
public class EventRepositoryStub extends AbstractRepositoryStub<EventEntity> implements EventRepositoryInterface {
    public EventRepositoryStub() {
        var ee1 = new EventEntity();
        ee1.Title = "International 12";
        ee1.FirstTeamId = 1;
        ee1.SecondTeamId = 2;
        ee1.Date = Date.from(Instant.now());

        Create(ee1);

        var ee2 = new EventEntity();
        ee2.Title = "Brawl Stars Championship";
        ee2.FirstTeamId = 3;
        ee2.SecondTeamId = 4;
        ee2.Date = Date.from(Instant.now());

        Create(ee2);
    }
}
