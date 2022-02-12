package com.example.sportevents.model;

public class EventResult extends Model {
    private final int Id;

    private Event Event;

    private final int FirstTeamScore;
    private final int SecondTeamScore;

    private Team Winner;

    public EventResult(int id, int firstTeamScore, int secondTeamScore) {
        Id = id;
        FirstTeamScore = firstTeamScore;
        SecondTeamScore = secondTeamScore;
    }

    public int getId() {
        return Id;
    }

    public com.example.sportevents.model.Event getEvent() {
        return Event;
    }

    public int getFirstTeamScore() {
        return FirstTeamScore;
    }

    public int getSecondTeamScore() {
        return SecondTeamScore;
    }

    public Team getWinner() {
        return Winner;
    }

    public void setEvent(com.example.sportevents.model.Event event) {
        Event = event;
    }

    public void setWinner(Team winner) {
        Winner = winner;
    }
}
