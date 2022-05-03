package com.example.sportevents.controllers;
import com.example.sportevents.model.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sportevents.service.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@org.springframework.stereotype.Controller
public class UserController {

    private EventResultService eventResultService;
    private EventService eventService;
    private TeamService teamService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Team.class,
                new TeamEditor());
    }

    public UserController(EventResultService _resultService, EventService _eventService, TeamService _teamService) {

        eventResultService = _resultService;
        eventService = _eventService;
        teamService = _teamService;

    }

    @GetMapping("/")
    public String GoHomeUser(Model model) {
        model.addAttribute("events", eventService.GetAll());
        return "index";

    }

    @GetMapping("teams")
    public String GetAllTeamsUser(Model model) {

        model.addAttribute("teams", teamService.GetAll());

        return "teams";
    }
    @GetMapping("/EventArchive")
    public String GoToArchiveUser(Model model) {

        model.addAttribute("results", eventResultService.GetAll());

        return "EventArchive";


    }
    @GetMapping("/TeamsAndEvents")
    public String GoToTeamAndEventsUser(Model model) {

        model.addAttribute("events", eventService.GetAll());

        return "TeamsAndEvents";
    }

    @GetMapping("/searchevents")
    public String SearchEvent(@RequestParam(value = "searchinput") String inputSearch,
                              Model model){

        model.addAttribute("events", eventService.FindEventsByText(inputSearch));

        return "index";
    }
    @GetMapping("/searchteams")
    public String SearchTeam(@RequestParam(value = "searchinput") String input,
                             Model model){

        model.addAttribute("teams", teamService.FindTeamsByText(input));

        return "TeamsAdmin";
    }

    @GetMapping("/searchresults")
    public String SearchResult(@RequestParam(value = "searchinput") String input,
                               Model model){
        model.addAttribute("results", eventResultService.FindResultsByText(input));

        return "EventArchiveAdmin";
    }
}
