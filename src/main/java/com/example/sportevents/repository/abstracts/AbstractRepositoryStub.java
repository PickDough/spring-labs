package com.example.sportevents.repository.abstracts;

import com.example.sportevents.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepositoryStub<TEntity extends Entity> implements RepositoryInterface<TEntity> {
    protected ArrayList<TEntity> entities = new ArrayList<>();

    @Override
    public void Create(TEntity entity) {
        entity.Id = entity.autoIncrement();
        entities.add(entity);
    }

    @Override
    public Optional<TEntity> Read(int id) {
        return entities.stream().filter(e -> e.getId() == id).findFirst();
    }

    @Override
    public List<TEntity> ReadAll() {
        return entities;
    }

    @Override
    public void Update(TEntity entity) {
        var e = Read(entity.getId());
        if (e.isEmpty())
            return;

        var ind = entities.indexOf(e.get());
        entities.set(ind, entity);
    }

    @Override
    public void Delete(int id) {
        var e = Read(id);
        if (e.isEmpty())
            return;

        entities.remove(e.get());
    }
}
