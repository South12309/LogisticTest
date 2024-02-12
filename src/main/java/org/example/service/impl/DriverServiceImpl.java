package org.example.service.impl;

import org.example.model.DriverEntity;
import org.example.repository.DriverEntityRepository;
import org.example.service.DriverService;

import java.util.List;
import java.util.UUID;

public class DriverServiceImpl implements DriverService {
    private DriverEntityRepository repository;
    @Override
    public DriverEntity save(DriverEntity driverEntity) {
        repository.save(driverEntity);
        return null;
    }

    @Override
    public DriverEntity findById(Integer uuid) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<DriverEntity> findAll() {
        return null;
    }
}
