package com.example.sportevents.model;

import java.util.Date;

public class Event extends Model {

    public Event(int id, String title, java.util.Date date) {
        Id = id;
        Title = title;
        Date = date;
    }

    private final int Id;

    private final String Title;

    private Team FirstTeam;
    private Team SecondTeam;

    private final Date Date;

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public Team getFirstTeam() {
        return FirstTeam;
    }

    public Team getSecondTeam() {
        return SecondTeam;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setFirstTeam(Team firstTeam) {
        FirstTeam = firstTeam;

    }

    public void setSecondTeam(Team secondTeam) {
        SecondTeam = secondTeam;

    }
}
