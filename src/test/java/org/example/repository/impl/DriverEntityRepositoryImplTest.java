package org.example.repository.impl;

import org.example.model.DriverEntity;
import org.example.repository.DriverEntityRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverEntityRepositoryImplTest {
    private DriverEntityRepository driverEntityRepository= DriverEntityRepositoryImpl.getINSTANCE();

    @Test
    void getINSTANCE() {
    }

    @Test
    void findById() {
        DriverEntity byId = driverEntityRepository.findById(1).get();
      //  System.out.println(byId.getFio());
        System.out.println(byId.getId());
        System.out.println(byId.getTrucks());
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void save() {
        DriverEntity driverEntity = new DriverEntity();
      //  driverEntity.setFio("fio");
        driverEntityRepository.save(driverEntity);
        System.out.println(driverEntity.getId());
    }

    @Test
    void update() {
    }
}