package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.ConnectionManagerImpl;
import org.example.db.PropertiesUtil;
import org.example.model.DriverEntity;
import org.example.model.TruckEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.DriverTruckEntityRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@Testcontainers
class DriverTruckEntityRepositoryImplTest {
    private static DriverTruckEntityRepository repository;
    private static ConnectionManager connectionManager;
    @Container
    static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("logistic")
            .withInitScript("db/migration/V1__init.sql");

    @BeforeAll
    static void beforeAll() {
        Properties testProperties = new Properties();
        testProperties.put("jdbcUrl", CONTAINER.getJdbcUrl());
        testProperties.put("username", CONTAINER.getUsername());
        testProperties.put("password", CONTAINER.getPassword());
        try (MockedStatic<PropertiesUtil> propertiesUtilMockedStatic = mockStatic(PropertiesUtil.class)) {
            propertiesUtilMockedStatic.when(PropertiesUtil::getProperties).thenReturn(testProperties);
            connectionManager = ConnectionManagerImpl.getInstance();
            repository = DriverTruckEntityRepositoryImpl.getINSTANCE();
        }
    }
    @AfterAll
    static void afterAll() {
        connectionManager.close();
    }
    @Test
    void getINSTANCE() {
    }

    @Test
    void findTrucksByDriverId() {
        List<TruckEntity> trucksByDriverId = repository.findTrucksByDriverId(1);
        assertEquals(2, trucksByDriverId.size());
    }

    @Test
    void findDriversByTruckId() {
        List<DriverEntity> driversByTruckId = repository.findDriversByTruckId(1);
        assertEquals(3, driversByTruckId.size());
    }
}