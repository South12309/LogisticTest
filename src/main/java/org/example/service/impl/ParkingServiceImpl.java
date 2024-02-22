package org.example.service.impl;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.ParkingEntityRepository;
import org.example.repository.impl.DriverEntityRepositoryImpl;
import org.example.repository.impl.ParkingEntityRepositoryImpl;
import org.example.service.DriverService;
import org.example.service.ParkingService;

import java.util.List;
import java.util.UUID;

public class ParkingServiceImpl implements ParkingService {
    private final ParkingEntityRepository repository;

    public ParkingServiceImpl() {
        repository = ParkingEntityRepositoryImpl.getINSTANCE();
    }
    @Override
    public ParkingEntity save(ParkingEntity parkingEntity) {
        return repository.save(parkingEntity);
    }

    @Override
    public ParkingEntity findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public List<ParkingEntity> findAll() {
        return repository.findAll();
    }
}
