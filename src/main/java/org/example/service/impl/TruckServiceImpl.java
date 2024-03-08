package org.example.service.impl;

import org.example.model.TruckEntity;
import org.example.repository.TruckEntityRepository;
import org.example.repository.impl.TruckEntityRepositoryImpl;
import org.example.service.TruckService;

import java.util.List;
import java.util.UUID;

public class TruckServiceImpl implements TruckService {
    private TruckEntityRepository repository;

    public TruckServiceImpl() {
        repository = TruckEntityRepositoryImpl.getINSTANCE();
    }

    public TruckServiceImpl(TruckEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public TruckEntity save(TruckEntity truckEntity) {
        if (repository.findById(truckEntity.getId()).isEmpty()) {
            return repository.save(truckEntity);
        }
        return repository.update(truckEntity);
    }

    @Override
    public TruckEntity findById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public Boolean delete(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public List<TruckEntity> findAll() {
        return repository.findAll().get();
    }
}
