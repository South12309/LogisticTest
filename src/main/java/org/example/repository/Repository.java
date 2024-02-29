package org.example.repository;

import org.example.model.TruckEntity;

import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {

    Optional<T> findById(K id);

    boolean deleteById(K id);

    Optional<List<T>> findAll();

    T save(T t);
    T update(T t);
}
