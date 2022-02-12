package com.example.sportevents.model;

public class Team extends Model {

    public Team(int id, String name) {
        Id = id;
        Name = name;
    }

    private final int Id;

    private final String Name;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
