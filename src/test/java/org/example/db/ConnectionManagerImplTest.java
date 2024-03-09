package org.example.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
@Testcontainers
class ConnectionManagerImplTest {
    private static ConnectionManager connectionManager;
    @Container
    static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("logistic")
            .withInitScript("db/migration/V1__init.sql");
    @BeforeAll
    static void beforeAll() throws SQLException {
        Properties testProperties = new Properties();
        testProperties.put("jdbcUrl", CONTAINER.getJdbcUrl());
        testProperties.put("username", CONTAINER.getUsername());
        testProperties.put("password", CONTAINER.getPassword());
        try (MockedStatic<PropertiesUtil> propertiesUtilMockedStatic = mockStatic(PropertiesUtil.class)) {
            propertiesUtilMockedStatic.when(PropertiesUtil::getProperties).thenReturn(testProperties);
            connectionManager = ConnectionManagerImpl.getInstance();
        }
    }

    @AfterAll
    static void afterAll() throws SQLException {

    }

    @Test
    void getConnection() {
        assertDoesNotThrow(() -> {
            try (Connection connection = connectionManager.getConnection()) {
            }
        });

    }
}