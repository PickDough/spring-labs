package com.example.sportevents.service.abstracts;

import com.example.sportevents.model.Team;
import com.example.sportevents.service.abstracts.ServiceInterface;

import java.util.List;

public interface TeamServiceInterface extends ServiceInterface<Team> {
    List<Team> GetByName(String name);
}
