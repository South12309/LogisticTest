package org.example.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class ConnectionManagerImplTest {
    private static ConnectionManager manager;
    @Container
    static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:15-alpine");

    @BeforeAll
    static void beforeAll() throws SQLException {
        var testProperties = new Properties();
        testProperties.put("jdbcUrl", CONTAINER.getJdbcUrl());
        testProperties.put("username", CONTAINER.getUsername());
        testProperties.put("password", CONTAINER.getPassword());
        CONTAINER.start();
        manager = ConnectionManagerImpl.getInstance();

        try (MockedStatic<PropertiesUtil> staticMock = mockStatic(PropertiesUtil.class)) {
            staticMock.when(PropertiesUtil::getProperties).thenReturn(testProperties);
        }
        manager = ConnectionManagerImpl.getInstance();

    }

    @AfterAll
    static void afterAll() throws SQLException {
        manager.close();
        CONTAINER.stop();

    }

    @Test
    void getConnection() {
        assertDoesNotThrow(() -> {
            try (Connection connection = manager.getConnection()) {
            }
        });

    }
}