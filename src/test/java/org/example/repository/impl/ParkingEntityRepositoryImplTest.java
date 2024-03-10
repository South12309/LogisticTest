package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.ConnectionManagerImpl;
import org.example.db.PropertiesUtil;
import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.ParkingEntityRepository;
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
class ParkingEntityRepositoryImplTest {
    private static ParkingEntityRepository repository;
    private static ConnectionManager connectionManager;
    private static MockedStatic<PropertiesUtil> propertiesUtilMockedStatic;
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
        propertiesUtilMockedStatic = mockStatic(PropertiesUtil.class);
        propertiesUtilMockedStatic.when(PropertiesUtil::getProperties).thenReturn(testProperties);
        connectionManager = ConnectionManagerImpl.getInstance();
        repository = ParkingEntityRepositoryImpl.getINSTANCE();
    }


@AfterAll
static void afterAll() {
    connectionManager.close();
    propertiesUtilMockedStatic.close();
}

@Test
void findById() {
    Optional<ParkingEntity> parkingEntity = repository.findById(1);
    assertTrue(!parkingEntity.isEmpty());
    assertEquals(1, parkingEntity.get().getId());
    assertEquals("cherkessk", parkingEntity.get().getAddress());
}

@Test
void findAll() {
    Optional<List<ParkingEntity>> all = repository.findAll();
    assertEquals(3, all.get().size());
}

@Test
void deleteById() {
    assertTrue(repository.deleteById(1));
    assertTrue(!repository.deleteById(1));
}

@Test
void save() {
    ParkingEntity parkingEntity = new ParkingEntity();
    parkingEntity.setAddress("Stavropol1");
    parkingEntity.setSquare(30);
    ParkingEntity save = repository.save(parkingEntity);
    assertEquals(save.getAddress(), parkingEntity.getAddress());
    assertEquals(save.getSquare(), parkingEntity.getSquare());
    assertEquals(4, save.getId());
}

@Test
void update() {
    ParkingEntity parkingEntityTest = new ParkingEntity();
    parkingEntityTest.setId(2);
    parkingEntityTest.setAddress("Kislovodsk");
    parkingEntityTest.setSquare(50);
    ParkingEntity driverEntityFromDB = repository.findById(1).get();
    assertNotEquals(parkingEntityTest.getAddress(), driverEntityFromDB.getAddress());
    ParkingEntity save = repository.update(parkingEntityTest);
    assertEquals(save.getAddress(), parkingEntityTest.getAddress());
    assertEquals(save.getSquare(), parkingEntityTest.getSquare());
    assertEquals(2, save.getId());
}
}