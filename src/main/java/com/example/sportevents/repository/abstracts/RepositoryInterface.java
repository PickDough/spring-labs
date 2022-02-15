package com.example.sportevents.repository.abstracts;

import com.example.sportevents.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<TEntity extends Entity> {
    int Create(TEntity entity);
    Optional<TEntity> Read(int id);
    List<TEntity> ReadAll();
    void Update(TEntity entity);
    void Delete(int id);
}
