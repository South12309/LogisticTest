package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {
    private static HikariConfig config = new HikariConfig("properties.ini");
    private static HikariDataSource hikariDataSource = new HikariDataSource(config);
    private ConnectionManagerImpl() {

    }
    @Override
    public Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
