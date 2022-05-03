package com.example.sportevents.service;

import com.example.sportevents.entity.EventEntity;
import com.example.sportevents.entity.EventResultEntity;
import com.example.sportevents.mapper.MapperInterface;
import com.example.sportevents.model.Event;
import com.example.sportevents.model.EventResult;
import com.example.sportevents.repository.abstracts.EventResultRepositoryInterface;
import com.example.sportevents.repository.abstracts.RepositoryInterface;
import com.example.sportevents.service.abstracts.AbstractService;
import com.example.sportevents.service.abstracts.EventResultServiceInterface;
import com.example.sportevents.service.abstracts.EventServiceInterface;
import com.example.sportevents.service.abstracts.TeamServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class EventResultService extends AbstractService<EventResult, EventResultEntity> implements EventResultServiceInterface {
    record Pair(EventResultEntity ee, EventResult e) {}

    private final EventServiceInterface eventService;
    private final TeamServiceInterface teamService;
    private final EventResultRepositoryInterface eventResultRepository;

    public EventResultService(
            MapperInterface<EventResult, EventResultEntity> mapper,
            RepositoryInterface<EventResultEntity> repository,
            EventServiceInterface eventService,
            TeamServiceInterface teamService,
            EventResultRepositoryInterface eventResultRepository) {
        super(mapper, repository);
        this.eventService = eventService;
        this.teamService = teamService;
        this.eventResultRepository = eventResultRepository;
    }

    @Override
    public Optional<EventResult> Get(int id) {
        var entity = eventResultRepository.Read(id);
        var model = entity.map(mapper::ToModel);
        if (model.isEmpty())
            return model;

        return Optional.of(setEventWinner(entity.get(), model.get()));
    }

    @Override
    public List<EventResult> GetAll() {
        return eventResultRepository.ReadAll().stream()
                .map(e -> new Pair(e, mapper.ToModel(e)))
                .map(p -> setEventWinner(p.ee, p.e))
                .toList();
    }

    private EventResult setEventWinner(EventResultEntity entity, EventResult model) {
        var event = eventService.Get(entity.EventId);
        var winner = teamService.Get(entity.WinnerId);

        event.ifPresent(model::setEvent);
        winner.ifPresent(model::setWinner);

        return model;
    }

    public List<EventResult> FindResultsByText(String inputString){

        ArrayList<EventResult> foundResults = new ArrayList<EventResult>();

        for(EventResult ev : GetAll()){

            String eventResString = ev.getEvent().getFirstTeam().getName() + " " + ev.getEvent().getSecondTeam().getName();
            if (eventResString.matches(".*" + Pattern.quote(inputString) + ".*")){
                foundResults.add(ev);
            }
        }

        return foundResults;

    }

}
