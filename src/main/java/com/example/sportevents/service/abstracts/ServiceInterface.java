package com.example.sportevents.service.abstracts;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<TModel> {
    void Add(TModel entity);
    Optional<TModel> Get(int id);
    List<TModel> GetAll();
    void Update(TModel entity);
    void Remove(int id);
}
