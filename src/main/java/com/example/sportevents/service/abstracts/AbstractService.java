package com.example.sportevents.service.abstracts;

import com.example.sportevents.entity.Entity;
import com.example.sportevents.mapper.MapperInterface;
import com.example.sportevents.model.Model;
import com.example.sportevents.repository.abstracts.RepositoryInterface;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<TModel extends Model, TEntity extends Entity> implements ServiceInterface<TModel> {
    protected final MapperInterface<TModel, TEntity> mapper;
    private final RepositoryInterface<TEntity> repository;

    public AbstractService(MapperInterface<TModel, TEntity> mapper, RepositoryInterface<TEntity> repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public void Add(TModel model) {
        repository.Create(mapper.ToEntity(model));
    }

    @Override
    public Optional<TModel> Get(int id) {
        return repository.Read(id).flatMap(e -> Optional.of(mapper.ToModel(e)));
    }

    @Override
    public List<TModel> GetAll() {
        return repository.ReadAll().stream().map(mapper::ToModel).toList();
    }

    @Override
    public void Update(TModel model) {
        repository.Update(mapper.ToEntity(model));
    }

    @Override
    public void Remove(int id) {
        repository.Delete(id);
    }
}
