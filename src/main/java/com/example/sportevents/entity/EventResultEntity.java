package com.example.sportevents.entity;

public class EventResultEntity extends Entity {
    public static int id = 1;

    public int EventId;

    public int FirstTeamScore;
    public int SecondTeamScore;

    public int WinnerId;

    @Override
    public int autoIncrement() {
        return id++;
    }
}
