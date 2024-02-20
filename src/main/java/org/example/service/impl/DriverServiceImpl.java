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
        return repository.save(driverEntity);
    }

    @Override
    public DriverEntity findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public List<DriverEntity> findAll() {
        return repository.findAll();
    }
}
