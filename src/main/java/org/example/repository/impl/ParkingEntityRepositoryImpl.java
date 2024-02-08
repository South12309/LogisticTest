package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.ConnectionManagerImpl;
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
import java.util.List;
import java.util.UUID;

public class ParkingEntityRepositoryImpl implements ParkingEntityRepository {
    private ParkingResultSetMapper resultSetMapper;
   // private ConnectionManager connectionManager;

    @Override
    public ParkingEntity findById(Integer id) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM parkings where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapOneResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM parkings where id=?");
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ParkingEntity> findAll() {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM parkings");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapListResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ParkingEntity save(ParkingEntity parkingEntity) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO parkings (address, square) VALUES(?, ?)");
            preparedStatement.setString(1, parkingEntity.getAddress());
            preparedStatement.setInt(2, parkingEntity.getSquare());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                parkingEntity.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Error insert");
            }
            return parkingEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ParkingEntity update(ParkingEntity parkingEntity) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE parkings SET address = ?, square = ? WHERE id = ?");
            preparedStatement.setObject(1, parkingEntity.getAddress());
            preparedStatement.setObject(2,parkingEntity.getSquare());
            preparedStatement.setObject(3,parkingEntity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetMapper.mapOneResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
