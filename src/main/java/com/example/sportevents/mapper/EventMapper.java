package com.example.sportevents.mapper;

import com.example.sportevents.entity.EventEntity;
import com.example.sportevents.model.Event;

public class EventMapper implements MapperInterface<Event, EventEntity> {

    @Override
    public Event ToModel(EventEntity eventEntity) {
        return new Event(eventEntity.Id, eventEntity.Title, eventEntity.Date);
    }

    @Override
    public EventEntity ToEntity(Event event) {
        var e = new EventEntity();
        e.Id = event.getId();
        e.Title = event.getTitle();
        e.FirstTeamId = event.getFirstTeam().getId();
        e.SecondTeamId = event.getSecondTeam().getId();
        e.Date = event.getDate();

        return e;
    }
}
