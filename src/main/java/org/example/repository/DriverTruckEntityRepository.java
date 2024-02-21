package org.example.repository;

import org.example.model.DriverEntity;
import org.example.model.DriverTruckEntity;
import org.example.model.TruckEntity;

import java.util.List;

public interface DriverTruckEntityRepository { //extends Repository<DriverTruckEntity, Integer> {
    List<TruckEntity> findTrucksByDriverId(Integer driverId);
    List<DriverEntity> findDriversByTruckId(Integer truckId);
}

