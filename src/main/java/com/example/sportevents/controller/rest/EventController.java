package com.example.sportevents.controller.rest;

import com.example.sportevents.model.Event;
import com.example.sportevents.model.Team;
import com.example.sportevents.service.EventService;
import com.example.sportevents.service.abstracts.EventServiceInterface;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventServiceInterface eventService;

    public EventController(EventServiceInterface eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAll(){
        return eventService.GetAll();
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable int id) {
        return eventService.Get(id).orElse(null);
    }

    @PostMapping
    public void add(@Valid Event event){
        eventService.Add(event);
    }

    @PatchMapping
    public void update(@Valid Event event){
        eventService.Update(event);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        eventService.Remove(id);
    }
}
