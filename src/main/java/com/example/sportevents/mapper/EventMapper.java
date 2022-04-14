package com.example.sportevents.mapper;

import com.example.sportevents.entity.EventEntity;
import com.example.sportevents.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper implements MapperInterface<Event, EventEntity> {

    @Override
    public Event ToModel(EventEntity eventEntity) {

        var event = new Event();
        event.setId(eventEntity.Id);
        event.setTitle(eventEntity.Title);
        event.setDate(eventEntity.Date);
        return event;
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
