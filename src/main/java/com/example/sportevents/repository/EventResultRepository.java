package com.example.sportevents.repository;

import com.example.sportevents.entity.EventResultEntity;
import com.example.sportevents.repository.abstracts.EventResultRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventResultRepository implements EventResultRepositoryInterface {
    @Override
    public int Create(EventResultEntity entity) {
        return 0;
    }

    @Override
    public Optional<EventResultEntity> Read(int id) {
        return Optional.empty();
    }

    @Override
    public List<EventResultEntity> ReadAll() {
        return null;
    }

    @Override
    public void Update(EventResultEntity entity) {

    }

    @Override
    public void Delete(int id) {

    }
}
