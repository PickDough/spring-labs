package com.example.sportevents.controller.rest;

import com.example.sportevents.model.Team;
import com.example.sportevents.service.abstracts.TeamServiceInterface;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamServiceInterface teamService;

    public TeamController(TeamServiceInterface teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAll(
            @RequestParam(value = "name", defaultValue = "", required = false) @NotNull String name,
            @RequestParam(value = "limit", defaultValue = "0", required = false) int limit,
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset
    ) {
        return teamService.GetAllWithFilterAndPagination(name, limit, offset);
    }

    @GetMapping("/{id}")
    public Team get(@PathVariable int id) {
        return teamService.Get(id).orElse(null);
    }

    @PostMapping
    public void add(@Valid Team team) {
        teamService.Add(team);
    }

    @PatchMapping
    public void update(@Valid Team team) {
        teamService.Update(team);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        teamService.Remove(id);
    }
}
