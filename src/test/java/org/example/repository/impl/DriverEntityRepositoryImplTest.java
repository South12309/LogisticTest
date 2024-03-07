package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.ConnectionManagerImpl;
import org.example.db.PropertiesUtil;
import org.example.model.DriverEntity;
import org.example.model.TruckEntity;
import org.example.repository.DriverEntityRepository;
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
class DriverEntityRepositoryImplTest {
    private static DriverEntityRepository repository;
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
        repository = DriverEntityRepositoryImpl.getINSTANCE();
        repository.setManager(connectionManager);
    }

    @Test
    void getINSTANCE() {
    }

    @Test
    void findById() {
        Optional<DriverEntity> driverEntity = repository.findById(1);
        assertTrue(!driverEntity.isEmpty());
        assertEquals(1, driverEntity.get().getId());
        assertEquals("Ivanov", driverEntity.get().getSurname());
    }

    @Test
    void findAll() {
        Optional<List<DriverEntity>> all = repository.findAll();
        assertEquals(3, all.get().size());
    }


    @Test
    void save() {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setSurname("Voronov");
        driverEntity.setName("Vladimir");
        driverEntity.setPatronymic("Ivanovich");
        DriverEntity save = repository.save(driverEntity);
        assertEquals(save.getSurname(), driverEntity.getSurname());
        assertEquals(save.getName(), driverEntity.getName());
        assertEquals(save.getPatronymic(), driverEntity.getPatronymic());
        assertEquals(4, save.getId());
    }


    @Test
    void update() {
        DriverEntity driverEntityTest = new DriverEntity();
        driverEntityTest.setId(2);
        driverEntityTest.setSurname("Larinov");
        driverEntityTest.setName("Nikolay");
        driverEntityTest.setPatronymic("Vitalievich");
        DriverEntity driverEntityFromDB = repository.findById(1).get();
        assertNotEquals(driverEntityTest.getSurname(), driverEntityFromDB.getSurname());
        DriverEntity save = repository.update(driverEntityTest);
        assertEquals(save.getSurname(), driverEntityTest.getSurname());
        assertEquals(save.getName(), driverEntityTest.getName());
        assertEquals(save.getPatronymic(), driverEntityTest.getPatronymic());
        assertEquals(2, save.getId());
    }
    @Test
    void deleteById() {
        assertTrue(repository.deleteById(1));
        assertTrue(!repository.deleteById(1));
    }
}