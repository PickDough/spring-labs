package com.example.sportevents.entity;

import java.util.Date;

public class EventEntity extends Entity {
    public static int id = 1;

    public String Title;

    public int FirstTeamId;
    public int SecondTeamId;

    public Date Date;

    @Override
    public int autoIncrement() {
        return id++;
    }
}
