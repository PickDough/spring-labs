package com.example.sportevents.controller.rest;

import com.example.sportevents.model.EventResult;
import com.example.sportevents.model.Team;
import com.example.sportevents.service.abstracts.EventResultServiceInterface;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/event-result")
public class EventResultController {
    private final EventResultServiceInterface eventResultService;

    public EventResultController(EventResultServiceInterface eventResultService) {
        this.eventResultService = eventResultService;
    }

    @GetMapping
    public List<EventResult> getAll(){
        return eventResultService.GetAll();
    }

    @GetMapping("/{id}")
    public EventResult get(@PathVariable int id) {
        return eventResultService.Get(id).orElse(null);
    }

    @PostMapping
    public void add(@Valid EventResult eventResult){
        eventResultService.Add(eventResult);
    }

    @PatchMapping
    public void update(@Valid EventResult eventResult){
        eventResultService.Update(eventResult);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        eventResultService.Remove(id);
    }
}
