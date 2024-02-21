package org.example.repository;

import org.example.model.TruckEntity;

import java.util.List;

public interface Repository<T, K> {
    List<T> findTrucksByDriverId(K driverId);

    List<T> findDriversByTruckId(K truckId);
    T findById(K id);

    boolean deleteById(K id);

    List<T> findAll();

    T save(T t);
    T update(T t);
}
