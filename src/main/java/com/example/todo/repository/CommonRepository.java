package com.example.todo.repository;

import java.util.Collection;
import java.util.Collections;

public interface CommonRepository<T> {

    T save(T entity);

    Iterable<T> save(Collection<T> entities);

    void delete(T entity);

    T findById(String id);

    Iterable<T> findAll();

}
