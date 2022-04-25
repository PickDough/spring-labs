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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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

    public Controller(EventResultService _resultService, EventService _eventService, TeamService _teamService) {

        eventResultService = _resultService;
        eventService = _eventService;
        teamService = _teamService;

    }

    @GetMapping("/")
    public String GoHome(Model model) {
        model.addAttribute("events", eventService.GetAll());
        return "index";

    }

    @GetMapping("/TeamCreation")
    public String GoTeamCreation(Model model) {

        Team curteam = new Team(1, "");

        model.addAttribute("team", curteam);

        return "TeamCreation";
    }

    @PostMapping("/saveteam")
    public String SaveNewTeam(@ModelAttribute(value = "team") Team team, Model model) {

        teamService.Add(team);
        model.addAttribute("teams", teamService.GetAll());
        return "TeamsAdmin";

    }

    @GetMapping("teams")
    public String GetAllTeams(Model model) {

        model.addAttribute("teams", teamService.GetAll());

        return "teams";
    }

    @GetMapping("teamsAdmin")
    public String GetAllTeamsForAdmin(Model model) {

        model.addAttribute("teams", teamService.GetAll());

        return "teamsAdmin";
    }

    @GetMapping("/EventArchive")
    public String GoToArchive(Model model) {

        model.addAttribute("results", eventResultService.GetAll());

        return "EventArchive";


    }

    @GetMapping("EventArchiveAmdin")
    public String GoToArchiveAdmin(Model model) {

        model.addAttribute("results", eventResultService.GetAll());
        return "EventArchiveAdmin";
    }

    @GetMapping("/TeamsAndEvents")
    public String GoToTeamAndEvents(Model model) {

        model.addAttribute("events", eventService.GetAll());

        return "TeamsAndEvents";
    }

    @GetMapping("/eventCreationPage")
    public ModelAndView GoToEventCreation() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("event", new Event());
        modelAndView.addObject("listTeams", teamService.GetAll());
        modelAndView.setViewName("eventCreationPage");
        return modelAndView;

    }

    @GetMapping("/eventCreation")
    public ModelAndView EventCreation() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("eventForm", new EventForm(teamService.GetAll()));
        modelAndView.setViewName("eventCreation");
        return modelAndView;
    }


    @RequestMapping(value = "/eventCreation/saveevent", method = RequestMethod.POST)
    public String SaveEvent(@RequestParam(value = "DateParam") String dateStr,
                            @RequestParam(value = "FirstTeam") String idFirstStr,
                            @RequestParam(value = "SecondTeam") String idSecondStr,
                            @RequestParam(value = "Title") String title, Model model
    ) throws ParseException {

        Event curEvent = new Event();

        curEvent.setTitle(title);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        curEvent.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
        curEvent.setFirstTeam(teamService.Get(Integer.parseInt(idFirstStr)).get());
        curEvent.setSecondTeam(teamService.Get(Integer.parseInt(idSecondStr)).get());

        eventService.Add(curEvent);
        model.addAttribute("events", eventService.GetAll());
        return "TeamsAndEvents";
    }

    @GetMapping("/ResultCreation")
    public String GoToResultCreation(Model model) {

        model.addAttribute("listEvents", eventService.GetAll());
        model.addAttribute("result", new EventResult(1, 0, 0));

        return "ResultCreation";
    }

    @PostMapping("/ResultCreation/saveresult")
    public String CreateResult(@RequestParam(value = "eventId") String eventIdStr,
                               @RequestParam(value = "firstScore") String firstScoreStr,
                               @RequestParam(value = "secondScore") String secondScoreStr,
                               Model model) {
        EventResult curRes = new EventResult();
        curRes.setEvent(eventService.Get(Integer.parseInt(eventIdStr)).get());
        curRes.setFirstTeamScore(Integer.parseInt(firstScoreStr));
        curRes.setSecondTeamScore(Integer.parseInt(secondScoreStr));
        if (!(curRes.getSecondTeamScore() == curRes.getFirstTeamScore())) {
            if (curRes.getFirstTeamScore() > curRes.getSecondTeamScore()) {
                curRes.setWinner(teamService.Get(eventService.Get(curRes.getEvent().getId()).get().getFirstTeam().getId()).get());
            } else {
                curRes.setWinner(teamService.Get(eventService.Get(curRes.getEvent().getId()).get().getSecondTeam().getId()).get());
            }
        }

        eventResultService.Add(curRes);
        model.addAttribute("results", eventResultService.GetAll());
        return "EventArchiveAdmin";
    }

    @GetMapping("/EventArchiveAdmin")
    public String GoToEventArchiveAdminMode(Model model) {

        model.addAttribute("results", eventResultService.GetAll());

        return "EventArchiveAdmin";
    }


    @GetMapping("/TeamsAndEventsAdmin")
    public String GoToTeamAndEventsAdminMode(Model model) {

        model.addAttribute("events", eventService.GetAll());


        return "TeamsAndEventsAdmin";
    }

    @GetMapping("/gotoupdateresult")
    public String GoToUpdateResult(@RequestParam(value = "resultId") String resIdStr,
                                   Model model) {
        model.addAttribute("result", eventResultService.Get(Integer.parseInt(resIdStr)).get());
        model.addAttribute("listEvents", eventService.GetAll());
        return "ResultEdition";
    }

    @PostMapping("/ResultEdition/updateresult")
    public String UpdateResult(@RequestParam(value = "resultId") String resIdStr,
                               @RequestParam(value = "eventId") String eventIdStr,
                               @RequestParam(value = "firstScore") String firstScoreStr,
                               @RequestParam(value = "secondScore") String secondScoreStr,
                               Model model) {

        EventResult curRes = eventResultService.Get(Integer.parseInt(resIdStr)).get();
        curRes.setEvent(eventService.Get(Integer.parseInt(eventIdStr)).get());
        curRes.setFirstTeamScore(Integer.parseInt(firstScoreStr));
        curRes.setSecondTeamScore(Integer.parseInt(secondScoreStr));
        if (!(curRes.getSecondTeamScore() == curRes.getFirstTeamScore())) {
            if (curRes.getFirstTeamScore() > curRes.getSecondTeamScore()) {
                curRes.setWinner(teamService.Get(eventService.Get(curRes.getEvent().getId()).get().getFirstTeam().getId()).get());
            } else {
                curRes.setWinner(teamService.Get(eventService.Get(curRes.getEvent().getId()).get().getSecondTeam().getId()).get());
            }
        }
        eventResultService.Update(curRes);

        model.addAttribute("results", eventResultService.GetAll());
        return "EventArchiveAdmin";
    }

    @PostMapping("/deleteresult")
    public String DeleteResult(@RequestParam(value = "resultId") String resIdStr, Model model) {

        eventResultService.Remove(Integer.parseInt(resIdStr));
        model.addAttribute("results", eventResultService.GetAll());
        return "EventArchiveAdmin";
    }

    @GetMapping("/gotoupdateteam")
    public String GoToUpdateTeam(@RequestParam("teamId") String strID, Model model) {
        int Id = Integer.parseInt(strID);

        model.addAttribute("team", teamService.Get(Id).get());
        return "teamUpdate";
    }

    @PostMapping("/updateteam")
    public String UpdateTeam(@RequestParam("newName") String newName,
                             @RequestParam("teamId") String idStr, Model model) {
        Team curTeam = teamService.Get(Integer.parseInt(idStr)).get();
        curTeam.setName(newName);
        teamService.Update(curTeam);
        model.addAttribute("teams", teamService.GetAll());
        return "TeamsAdmin";
    }

    @PostMapping("/deleteteam")
    public String DeleteTeam(@RequestParam("teamId") String idStr, Model model) {

        teamService.Remove(Integer.parseInt(idStr));
        model.addAttribute("teams", teamService.GetAll());
        return "TeamsAdmin";
    }

    @GetMapping("/gotoupdateevent")
    public String GotToUpdateEvent(@RequestParam(value = "eventId") String teamIdStr, Model model) {

        model.addAttribute("event", eventService.Get(Integer.parseInt(teamIdStr)).get());
        model.addAttribute("listTeams", teamService.GetAll());
        return "EventEdition";
    }

    @PostMapping("/eventEdition/updateevent")
    public String UpdateTeam(@RequestParam(value = "eventId") String eventIdStr,
                             @RequestParam(value = "DateParam") String dateStr,
                             @RequestParam(value = "FirstTeam") String idFirstStr,
                             @RequestParam(value = "SecondTeam") String idSecondStr,
                             @RequestParam(value = "Title") String title, Model model) throws ParseException {
        Event curEvent = eventService.Get(Integer.parseInt(eventIdStr)).get();
        curEvent.setTitle(title);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        curEvent.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
        curEvent.setFirstTeam(teamService.Get(Integer.parseInt(idFirstStr)).get());
        curEvent.setSecondTeam(teamService.Get(Integer.parseInt(idSecondStr)).get());

        eventService.Update(curEvent);

        model.addAttribute("events", eventService.GetAll());
        return "TeamsAndEventsAdmin";
    }

    @PostMapping("/deleteevent")
    public String DeleteEvent(@RequestParam(value = "eventId") String eventIdStr,
                              Model model) {

        eventService.Remove(Integer.parseInt(eventIdStr));
        model.addAttribute("events", eventService.GetAll());
        return "TeamsAndEventsAdmin";
    }

}
