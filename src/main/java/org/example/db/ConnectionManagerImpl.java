package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {
    private static ConnectionManagerImpl INSTANCE;
    private final HikariDataSource hikariCP;

    static {
        loadDriver();
    }

    private ConnectionManagerImpl() {
        hikariCP = new HikariDataSource(preparedConfig());
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static HikariConfig preparedConfig() {
        HikariConfig config = new HikariConfig(PropertiesUtil.getProperties());
        config.setConnectionTimeout(10_000);
        config.setMaximumPoolSize(20);
        return config;
    }

    public static ConnectionManagerImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionManagerImpl();
        }
        return INSTANCE;
    }

    @Override
    public Connection getConnection() {
        try {
            return hikariCP.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void close() {
        INSTANCE = null;
    }
}
