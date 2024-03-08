package org.example.service.impl;

import org.example.model.DriverEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.impl.DriverEntityRepositoryImpl;
import org.example.service.DriverService;

import java.util.List;
import java.util.UUID;

public class DriverServiceImpl implements DriverService {
    private final DriverEntityRepository repository;

    public DriverServiceImpl() {
        repository = DriverEntityRepositoryImpl.getINSTANCE();
    }

    public DriverServiceImpl(DriverEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public DriverEntity save(DriverEntity driverEntity) {
        if (repository.findById(driverEntity.getId()).isEmpty()) {
            return repository.save(driverEntity);
        }
        return repository.update(driverEntity);
    }

    @Override
    public DriverEntity findById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public List<DriverEntity> findAll() {
        return repository.findAll().get();
    }
}
