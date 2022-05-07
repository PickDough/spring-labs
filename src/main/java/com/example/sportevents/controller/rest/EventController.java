package com.example.sportevents.controller.rest;

import com.example.sportevents.model.Event;
import com.example.sportevents.service.abstracts.EventServiceInterface;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.api.OpenApiResourceNotFoundException;
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
    public List<Event> getAll() {
        return eventService.GetAll();
    }


    @GetMapping("/{id}")
    @ResponseBody
    @ApiResponse(
            description = "Request to get event by Id",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            implementation = Event.class
                    )
            )
    )
    public Event get(@PathVariable int id) {
        return eventService
                .Get(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("event not found"));
    }

    @PostMapping
    public void add(@RequestBody Event event) {
        eventService.Add(event);
    }

    @PatchMapping
    public void update(@Valid Event event) {
        eventService.Update(event);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        eventService.Remove(id);
    }
}
