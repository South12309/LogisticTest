package org.example.service;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;

import java.util.List;
import java.util.UUID;

public interface ParkingService {
    ParkingEntity save(ParkingEntity parkingEntity);

    ParkingEntity findById(Integer id);
    Boolean delete(Integer id);

    List<ParkingEntity> findAll();
}
