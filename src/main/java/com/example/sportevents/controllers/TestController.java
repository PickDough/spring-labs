package com.example.sportevents.controllers;
import com.example.sportevents.model.Event;
import com.example.sportevents.model.EventResult;
import com.example.sportevents.model.Team;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.sportevents.service.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
public class TestController {

    private EventResultService eventResultService;
    private EventService eventService;
    private TeamService teamService;

    public TestController(EventResultService _resultService, EventService _eventService, TeamService _teamService){

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


        System.out.println(team.getName());
        //teamService.Add(team);

        //ra.addFlashAttribute("message", "The team was added successfully");

        return "EventArchive";

    }

    @GetMapping("/EventArchive")
    public String GoToArchive(Model model){

        //model.addAttribute("results", eventResultService.GetAll());

        PutTestEventResults(model);

        return "EventArchive";


    }

    @GetMapping("/TeamsAndEvents")
    public String GoToTeamAndEvents(Model model){

        //model.addAttribute("events", eventService.GetAll());

        PutTestEvents(model);

        return "TeamsAndEvents";
    }
    @GetMapping("/EventCreation")
    public ModelAndView GoToEventCreation(Model model){

        //model.addAttribute("listTeams", teamService.GetAll());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("event", new Event());
        modelAndView.addObject("listTeams", teamService.GetAll());
        modelAndView.setViewName("EventCreation");
        return modelAndView;

    }
    @PostMapping("/EventCreation/saveevent")
    public String SaveEvent(@ModelAttribute("event") Event event){

        System.out.println(event.getTitle());
        //eventService.Add(event);

        return "EventCreation";
    }
    @GetMapping("/ResultCreation")
    public String GoToResultCreation(Model model){

        //model.addAttribute("listEvents", eventService.GetAll());
        model.addAttribute("result", new EventResult(1, 0, 0));
        PutTestEvents(model);

        return "ResultCreation";
    }

    @GetMapping("/EventArchiveAdmin")
    public String GoToEventArchiveAdminMode(Model model){

        //model.addAttribute("results", eventResultService.GetAll());


        PutTestEventResults(model);

        return "EventArchiveAdmin";
    }


    @GetMapping("TeamsAndEventsAdmin")
    public String GoToTeamAndEventsAdminMode(Model model){

        //model.addAttribute("events", eventService.GetAll());

        PutTestEvents(model);

        return "TeamsAndEventsAdmin";
    }

    private void PutTestTeams(Model model){

        Team team1 = new Team(1, "Clowns");

        Team team2 = new Team(2, "Foxtrot");

        Team team3 = new Team(3, "Alpha");

        Team team4  = new Team(4, "Omega");

        Team team5 = new Team(5, "Israel");

        List<Team> listTeams = Arrays.asList(team1, team2, team3, team4, team5);

        model.addAttribute("listTeams", listTeams);

    }

    private void PutTestEventResults(Model model){

        Team team1 = new Team(1, "Clowns");

        Team team2 = new Team(2, "Foxtrot");

        Team team3 = new Team(3, "Alpha");

        Team team4  = new Team(4, "Omega");

        Team team5 = new Team(5, "Israel");

        Event ev1 = new Event(3,"Chess Tournament", team1, team2, new Date (2022, 1, 2));

        Event ev2 = new Event(4, "Valley Tournament", team1, team2,new Date (2024, 5, 11));

        Event ev3 = new Event(5, "Football Tournament", team1, team2, new Date (2020, 11, 23));

        Event ev4 = new Event(6, "Basketball Tournament", team1, team2, new Date(2019, 12, 13));

        List<Event> listEvents = Arrays.asList(ev1, ev2, ev3, ev4);

        model.addAttribute("listEvents", listEvents);

        ev1.setFirstTeam(team1); ev1.setSecondTeam(team2); ev2.setFirstTeam(team2); ev2.setSecondTeam(team3);
        ev3.setFirstTeam(team3); ev3.setSecondTeam(team4); ev4.setFirstTeam(team4); ev4.setSecondTeam(team1);

        EventResult res1 = new EventResult(1, 12, 10);

        res1.setEvent(ev1); res1.setWinner(team1);

        EventResult res2 = new EventResult(2, 3, 0); res2.setEvent(ev2); res2.setWinner(team2);

        EventResult res3 = new EventResult(3, 5, 7); res3.setEvent(ev3); res3.setWinner(team4);

        model.addAttribute("results", Arrays.asList(res1, res2, res3));
    }

    private void PutTestEvents(Model model){
        Team team1 = new Team(1, "Clowns");

        Team team2 = new Team(2, "Foxtrot");

        Team team3 = new Team(3, "Alpha");

        Team team4  = new Team(4, "Omega");

        Team team5 = new Team(5, "Israel");

        Event ev1 = new Event(3,"Chess Tournament", team1, team2, new Date (2022, 1, 2));

        Event ev2 = new Event(4, "Valley Tournament", team1, team2,new Date (2024, 5, 11));

        Event ev3 = new Event(5, "Football Tournament", team1, team2, new Date (2020, 11, 23));

        Event ev4 = new Event(6, "Basketball Tournament", team1, team2, new Date(2019, 12, 13));

        List<Event> listEvents = Arrays.asList(ev1, ev2, ev3, ev4);

        model.addAttribute("listEvents", listEvents);
        ev1.setFirstTeam(team1); ev1.setSecondTeam(team2); ev2.setFirstTeam(team2); ev2.setSecondTeam(team3);
        ev3.setFirstTeam(team3); ev3.setSecondTeam(team4); ev4.setFirstTeam(team4); ev4.setSecondTeam(team1);

        model.addAttribute("events", Arrays.asList(ev1, ev2, ev3, ev4));
    }
}
