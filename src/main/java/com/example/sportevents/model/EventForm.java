package com.example.sportevents.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EventForm {

    private List<Team> allTeams;

    public EventForm(List<Team> teams){

        bindEvents(teams);

    }

    public List<Team> getAll(){

        return allTeams;

    }

    public void bindEvents(List<Team> teams){

        allTeams = teams;

    }

    private Team SelectedFirstTeam;

    public Team SelectedSecondTeam;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public java.util.Date EventDate;

    public Date getDate(){

        return EventDate;

    }

    private String Title;

    public String getTitle(){

        return Title;

    }

    public void setTitle(String title){

        Title = title;

    }
}
