package org.example.service.impl;

import org.example.model.DriverEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceImplTest {

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
        DriverServiceImpl driverService = new DriverServiceImpl();
        List<DriverEntity> all = driverService.findAll();
        System.out.println(all);
    }
}