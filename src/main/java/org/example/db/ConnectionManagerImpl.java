package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManagerImpl { //implements ConnectionManager {
    private static final HikariConfig config = new HikariConfig("C:\\Users\\User\\IdeaProjects\\LogisticTest\\src\\main\\resources\\db.properties");
    private static final HikariDataSource hikariDataSource = new HikariDataSource(config);
    private ConnectionManagerImpl() {

    }
    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
