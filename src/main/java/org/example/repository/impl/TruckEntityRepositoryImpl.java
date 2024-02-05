package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.TruckEntity;
import org.example.repository.TruckEntityRepository;
import org.example.repository.mapper.TruckResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TruckEntityRepositoryImpl implements TruckEntityRepository {
    private TruckResultSetMapper resultSetMapper;
    private ConnectionManager connectionManager;

    @Override
    public TruckEntity findById(UUID id) {
        // Здесь используем try with resources
        try {
            Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.map(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(UUID id) {
        return false;
    }

    @Override
    public TruckEntity findAll() {
        return null;
    }

    @Override
    public TruckEntity save(TruckEntity driverEntity) {
        return null;
    }
}
