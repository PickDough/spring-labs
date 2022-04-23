package com.example.sportevents.model;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class EventResult extends Model {
    private int Id;

    private Event Event;

    private int FirstTeamScore;
    private int SecondTeamScore;

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

    public String getFinalScore() { return FirstTeamScore + " : " + SecondTeamScore; }

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
