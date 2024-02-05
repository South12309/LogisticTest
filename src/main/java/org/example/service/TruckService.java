package org.example.service;

import org.example.model.DriverEntity;
import org.example.model.TruckEntity;

import java.util.UUID;

public interface TruckService {
    TruckEntity save(TruckEntity truckEntity);

    TruckEntity findById(UUID uuid);
}
