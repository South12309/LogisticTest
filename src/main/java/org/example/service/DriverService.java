package org.example.service;

import org.example.model.DriverEntity;

import java.util.UUID;

public interface DriverService {
    DriverEntity save(DriverEntity driverEntity);

    DriverEntity findById(UUID uuid);
}
