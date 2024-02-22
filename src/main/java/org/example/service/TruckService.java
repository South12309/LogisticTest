package org.example.service;

import org.example.model.DriverEntity;
import org.example.model.TruckEntity;

import java.util.List;
import java.util.UUID;

public interface TruckService {
    TruckEntity save(TruckEntity truckEntity);
    TruckEntity findById(Integer id);
    Boolean delete(Integer id);
    List<TruckEntity> findAll();
}
