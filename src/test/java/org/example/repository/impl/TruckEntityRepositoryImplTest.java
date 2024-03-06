package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.ConnectionManagerImpl;
import org.example.db.PropertiesUtil;
import org.example.model.TruckEntity;
import org.example.repository.TruckEntityRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@Testcontainers
class TruckEntityRepositoryImplTest {
    private static TruckEntityRepository repository;
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
        MockedStatic<PropertiesUtil> propertiesUtilMockedStatic = mockStatic(PropertiesUtil.class);
        propertiesUtilMockedStatic.when(PropertiesUtil::getProperties).thenReturn(testProperties);
        connectionManager = ConnectionManagerImpl.getInstance();
        repository = TruckEntityRepositoryImpl.getINSTANCE();
        repository.setManager(connectionManager);
    }

    @AfterAll
    static void afterAll() {

    }

    @Test
    void getINSTANCE() {


    }

    @Test
    void findByIdIsPresent() {
        Optional<TruckEntity> truckEntityById = repository.findById(1);
        assertTrue(!truckEntityById.isEmpty());
        assertEquals(1, truckEntityById.get().getId());
        assertEquals("MAN", truckEntityById.get().getModel());
    }
    @Test
    void findByIdIsNotPresent() {
        Optional<TruckEntity> truckEntityById = repository.findById(4);
        assertTrue(truckEntityById.isEmpty());
    }

    @Test
    void findAll() {
        Optional<List<TruckEntity>> all = repository.findAll();
        assertEquals(3, all.get().size());
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertTrue(!repository.deleteById(1));
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void findByParkingId() {
    }
}