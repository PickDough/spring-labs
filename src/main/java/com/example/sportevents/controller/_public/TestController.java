package com.example.sportevents.controller._public;

import com.example.sportevents.model.Event;
import com.example.sportevents.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/event")
public class TestController {
    private final TeamService teamService;

    @Autowired
    public TestController(TeamService _teamService) {
        teamService = _teamService;
    }

    @GetMapping("/eventCreation")
    public ModelAndView GoToEventCreation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("event", new Event());
        modelAndView.addObject("listTeams", teamService.GetAll());
        modelAndView.setViewName("kek");

        return modelAndView;
    }

    @PostMapping("/saveEvent")
    public String SaveEvent(@ModelAttribute("event") Event event) {
        System.out.println(event.getTitle());

        return "kek";
    }
}
