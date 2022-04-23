package com.example.sportevents.controllers;
import com.example.sportevents.model.*;
import org.springframework.web.bind.WebDataBinder;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Team.class,
                new TeamEditor());
    }

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

        return "teams";

    }

    @GetMapping("teams")
    public String GetAllTeams(Model model){

        model.addAttribute("teams", teamService.GetAll());

        return "teams";
    }

    @GetMapping("teamsAdmin")
    public String GetAllTeamsForAdmin(Model model){

        model.addAttribute("teams", teamService.GetAll());

        return "teamsAdmin";
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
    @GetMapping("/eventCreation")
    public ModelAndView EventCreation(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("eventForm", new EventForm(teamService.GetAll()));
        modelAndView.setViewName("eventCreation");
        return modelAndView;
    }

    public String CreateEvent(Model model){



        return "EventArchiveAdmin";
    }

    @RequestMapping(value ="/eventCreation/saveevent", method = RequestMethod.POST)
    public String SaveEvent(@ModelAttribute("eventForm") EventForm form){

        Event curEvent = new Event();
        curEvent.setDate(form.getDate());
        curEvent.setFirstTeam(form.getSelectedFirstTeam());
        curEvent.setSecondTeam(form.getSelectedSecondTeam());
        curEvent.setTitle(form.getTitle());
        eventService.Add(curEvent);

        return "TeamsAndEvents";
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


    @GetMapping("/TeamsAndEventsAdmin")
    public String GoToTeamAndEventsAdminMode(Model model){

        model.addAttribute("events", eventService.GetAll());


        return "TeamsAndEventsAdmin";
    }

    @GetMapping("/gotoupdateteam")
    public String GoToUpdateTeam(@ModelAttribute("Id") String strID, Model model){
        int Id = Integer.parseInt(strID);

        model.addAttribute("team", teamService.Get(Id));
        return "teamUpdate";
    }

    @PostMapping("/updateteam")
    public String UpdateTeam(@ModelAttribute("team") Team team, Model model){

        teamService.Update(team);
        return "TeamsAdmin";
    }


}
