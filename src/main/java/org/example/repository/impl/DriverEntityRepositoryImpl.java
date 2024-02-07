package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.ConnectionManagerImpl;
import org.example.model.DriverEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.mapper.DriverResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DriverEntityRepositoryImpl implements DriverEntityRepository {
    private DriverResultSetMapper resultSetMapper;
   // private ConnectionManager connectionManager;

    @Override
    public DriverEntity findById(Integer id) {
        // Здесь используем try with resources
        try (Connection connection = ConnectionManagerImpl.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM drivers where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    @Override
    public DriverEntity findAll() {
        return null;
    }

    @Override
    public DriverEntity save(DriverEntity driverEntity) {
        return null;
    }
}
