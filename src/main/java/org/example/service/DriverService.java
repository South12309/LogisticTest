package org.example.service;

import org.example.model.DriverEntity;

import java.util.List;
import java.util.UUID;

public interface DriverService {
    DriverEntity save(DriverEntity driverEntity);

    DriverEntity findById(Integer id);

    Boolean delete(Integer id);

    List<DriverEntity> findAll();
}
