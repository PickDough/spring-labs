package com.example.sportevents.repository;

import com.example.sportevents.entity.EventEntity;
import com.example.sportevents.repository.abstracts.AbstractRepositoryStub;
import com.example.sportevents.repository.abstracts.EventRepositoryInterface;
import com.example.sportevents.repository.abstracts.RepositoryInterface;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryStub extends AbstractRepositoryStub<EventEntity> implements EventRepositoryInterface {
}
