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

import java.sql.Connection;
import java.util.Optional;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class TruckEntityRepositoryImplTest {
    private static TruckEntityRepository repository;
    private static ConnectionManager connectionManager;
    @Container
    static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:16-alpine")
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
        repository.setManager(connectionManager);
    }

    @AfterAll
    static void afterAll() {

    }

    @Test
    void getINSTANCE() {


    }

    @Test
    void findById() {
        Optional<TruckEntity> byId = repository.findById(1);
        asser
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
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