package org.example.repository;

import org.example.model.TruckEntity;

import java.util.List;
import java.util.UUID;

public interface TruckEntityRepository extends Repository<TruckEntity, Integer> {
    List<TruckEntity> findByParkingId(Integer parkingId);
}

