package com.example.sportevents.entity;

public class TeamEntity extends Entity {
    public static int id = 1;
    public String Name;

    @Override
    public int autoIncrement() {
        return id++;
    }
}
