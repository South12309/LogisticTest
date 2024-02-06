package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.ParkingEntityRepository;
import org.example.repository.mapper.DriverResultSetMapper;
import org.example.repository.mapper.ParkingResultSetMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ParkingEntityRepositoryImpl implements ParkingEntityRepository {
    private ParkingResultSetMapper resultSetMapper;
    private ConnectionManager connectionManager;

    @Override
    public ParkingEntity findById(UUID id) {
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
    public ParkingEntity findAll() {
        return null;
    }

    @Override
    public ParkingEntity save(ParkingEntity parkingEntity) {
        return null;
    }
}
