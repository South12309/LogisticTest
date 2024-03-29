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

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@Testcontainers
class TruckEntityRepositoryImplTest {
    private static TruckEntityRepository repository;
    private static ConnectionManager connectionManager;
    private static MockedStatic<PropertiesUtil> propertiesUtilMockedStatic;
    @Container
    static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("postgres")
            .withInitScript("db/migration/V1__init.sql");

    @BeforeAll
    static void beforeAll() {
        Properties testProperties = new Properties();
        testProperties.put("jdbcUrl", CONTAINER.getJdbcUrl());
        testProperties.put("username", CONTAINER.getUsername());
        testProperties.put("password", CONTAINER.getPassword());
        propertiesUtilMockedStatic = mockStatic(PropertiesUtil.class);
        propertiesUtilMockedStatic.when(PropertiesUtil::getProperties).thenReturn(testProperties);
        connectionManager = ConnectionManagerImpl.getInstance();
        repository = TruckEntityRepositoryImpl.getINSTANCE();

    }

    @AfterAll
    static void afterAll() {
        connectionManager.close();
        propertiesUtilMockedStatic.close();
    }

    @Test
    void findByIdIsPresent() {
        Optional<TruckEntity> truckEntityById = repository.findById(3);
        assertTrue(!truckEntityById.isEmpty());
        assertEquals(3, truckEntityById.get().getId());
        assertEquals("Gazel", truckEntityById.get().getModel());

    }

    @Test
    void findByIdIsNotPresent() {
        Optional<TruckEntity> truckEntityById = repository.findById(40);
        assertTrue(truckEntityById.isEmpty());
    }

    @Test
    void findAll() {
        Optional<List<TruckEntity>> all = repository.findAll();
        assertEquals(3, all.get().size());
    }

    @Test
    void save() {
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setModel("kamaz");
        truckEntity.setNumber("A101AA09");
        TruckEntity save = repository.save(truckEntity);
        assertEquals(save.getNumber(), truckEntity.getNumber());
        assertEquals(save.getModel(), truckEntity.getModel());
        repository.deleteById(save.getId());
        assertEquals(4, save.getId());
    }

    @Test
    void update() {
        TruckEntity truckEntityTest = new TruckEntity();
        truckEntityTest.setId(2);
        truckEntityTest.setModel("kamaz");
        truckEntityTest.setNumber("A002AA09");
        TruckEntity truckEntityFromDB = repository.findById(2).get();
        assertNotEquals(truckEntityTest.getModel(), truckEntityFromDB.getModel());
        TruckEntity save = repository.update(truckEntityTest);
        assertEquals(save.getNumber(), truckEntityTest.getNumber());
        assertEquals(save.getModel(), truckEntityTest.getModel());
        assertEquals(2, save.getId());
    }

    @Test
    void findByParkingId() {
        List<TruckEntity> byParkingId = repository.findByParkingId(3);
        assertEquals(1, byParkingId.size());

    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertTrue(!repository.deleteById(1));
    }
}