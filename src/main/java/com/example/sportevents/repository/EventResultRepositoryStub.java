package com.example.sportevents.repository;

import com.example.sportevents.entity.EventResultEntity;
import com.example.sportevents.repository.abstracts.AbstractRepositoryStub;
import com.example.sportevents.repository.abstracts.EventResultRepositoryInterface;
import org.springframework.stereotype.Repository;

@Repository
public class EventResultRepositoryStub extends AbstractRepositoryStub<EventResultEntity> implements EventResultRepositoryInterface {
}
