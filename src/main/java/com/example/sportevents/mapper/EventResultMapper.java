package com.example.sportevents.mapper;

import com.example.sportevents.entity.EventResultEntity;
import com.example.sportevents.model.EventResult;
import org.springframework.stereotype.Component;

@Component
public class EventResultMapper implements MapperInterface<EventResult, EventResultEntity> {

    @Override
    public EventResult ToModel(EventResultEntity eventResultEntity) {
        return new EventResult(eventResultEntity.Id, eventResultEntity.FirstTeamScore, eventResultEntity.SecondTeamScore);
    }

    @Override
    public EventResultEntity ToEntity(EventResult eventResult) {
        var er = new EventResultEntity();
        er.Id = eventResult.getId();
        er.EventId = eventResult.getEvent().getId();
        er.FirstTeamScore = eventResult.getFirstTeamScore();
        er.SecondTeamScore = eventResult.getSecondTeamScore();
        er.WinnerId = eventResult.getWinner().getId();

        return er;
    }
}
