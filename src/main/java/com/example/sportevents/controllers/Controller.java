package com.example.sportevents.controllers;
import com.example.sportevents.model.Event;
import com.example.sportevents.model.EventResult;
import com.example.sportevents.model.Team;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sportevents.service.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {

    private EventResultService eventResultService;
    private EventService eventService;
    private TeamService teamService;

    public Controller(EventResultService _resultService, EventService _eventService, TeamService _teamService){

        eventResultService = _resultService;
        eventService = _eventService;
        teamService = _teamService;

    }

    @GetMapping("/HomePage")
    public String GoHome(){

        return "HomePage";

    }
    @GetMapping("/TeamCreation")
    public String GoTeamCreation(Model model){

        Team curteam = new Team(1, "");

        model.addAttribute("team", curteam);

        return "TeamCreation";
    }

    @PostMapping("/saveteam")
    public String SaveNewTeam(@ModelAttribute(value="team") Team team){

        teamService.Add(team);

        return "EventArchive";

    }

    @GetMapping("/EventArchive")
    public String GoToArchive(Model model){

        model.addAttribute("results", eventResultService.GetAll());

        return "EventArchive";


    }

    @GetMapping("/TeamsAndEvents")
    public String GoToTeamAndEvents(Model model){

        model.addAttribute("events", eventService.GetAll());

        return "TeamsAndEvents";
    }
    @GetMapping("/eventCreationPage")
    public ModelAndView GoToEventCreation(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("event", new Event());
        modelAndView.addObject("listTeams", teamService.GetAll());
        modelAndView.setViewName("eventCreationPage");
        return modelAndView;

    }
    @PostMapping("/EventCreation/saveevent")
    public String SaveEvent(@ModelAttribute("event") Event event){
        eventService.Add(event);

        return "EventCreation";
    }
    @GetMapping("/ResultCreation")
    public String GoToResultCreation(Model model){

        model.addAttribute("listEvents", eventService.GetAll());
        model.addAttribute("result", new EventResult(1, 0, 0));

        return "ResultCreation";
    }

    @GetMapping("/EventArchiveAdmin")
    public String GoToEventArchiveAdminMode(Model model){

        model.addAttribute("results", eventResultService.GetAll());

        return "EventArchiveAdmin";
    }


    @GetMapping("TeamsAndEventsAdmin")
    public String GoToTeamAndEventsAdminMode(Model model){

        model.addAttribute("events", eventService.GetAll());


        return "TeamsAndEventsAdmin";
    }

}
