package com.example.sportevents.entity;

public abstract class Entity {
    public int Id;

    public abstract int autoIncrement();

    public int getId() {
        return Id;
    }
}
