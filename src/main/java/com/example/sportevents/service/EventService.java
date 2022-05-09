package com.example.sportevents.service;

import com.example.sportevents.entity.EventEntity;
import com.example.sportevents.mapper.MapperInterface;
import com.example.sportevents.model.Event;
import com.example.sportevents.repository.abstracts.EventRepositoryInterface;
import com.example.sportevents.repository.abstracts.RepositoryInterface;
import com.example.sportevents.service.abstracts.AbstractService;
import com.example.sportevents.service.abstracts.EventServiceInterface;
import com.example.sportevents.service.abstracts.TeamServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class EventService extends AbstractService<Event, EventEntity> implements EventServiceInterface {
    record Pair(EventEntity ee, Event e) {}

    private final TeamServiceInterface teamService;
    private final EventRepositoryInterface eventRepository;

    public EventService(
            MapperInterface<Event, EventEntity> mapper,
            RepositoryInterface<EventEntity> repository,
            TeamServiceInterface teamService,
            EventRepositoryInterface eventRepository) {
        super(mapper, repository);
        this.teamService = teamService;
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> Get(int id) {
        var entity = eventRepository.Read(id);
        var model = entity.map(mapper::ToModel);

        if (model.isEmpty())
            return model;

        return Optional.of(setTeams(entity.get(), model.get()));
    }

    @Override
    public List<Event> GetAll() {
        return eventRepository.ReadAll().stream()
                .map(e -> new Pair(e, mapper.ToModel(e)))
                .map(p -> setTeams(p.ee, p.e))
                .toList();
    }

    private Event setTeams(EventEntity entity, Event model) {
        var firstTeam = teamService.Get(entity.FirstTeamId);
        var secondTeam = teamService.Get(entity.SecondTeamId);

        firstTeam.ifPresent(model::setFirstTeam);
        secondTeam.ifPresent(model::setSecondTeam);

        return model;
    }

    public List<Event> FindEventsByText(String inputString){

        ArrayList<Event> foundEvents = new ArrayList<Event>();

        for(Event ev : GetAll()){

            String eventString = ev.getTitle();
            if (eventString.matches(".*" + Pattern.quote(inputString) + ".*")){
                foundEvents.add(ev);
            }

        }
        if(foundEvents.isEmpty()){
            for(Event ev : GetAll()){
                String eventString = ev.getFirstTeam().getName() + ev.getSecondTeam().getName();
                if (eventString.matches(".*" + Pattern.quote(inputString) + ".*")){
                    foundEvents.add(ev);
                }
            }
        }

        return foundEvents;

    }
}
