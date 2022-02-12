package com.example.sportevents.mapper;

public interface MapperInterface<TModel, TEntity> {
    TModel ToModel(TEntity entity);
    TEntity ToEntity(TModel model);
}
