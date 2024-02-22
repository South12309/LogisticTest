package org.example.repository.impl;

import org.example.db.ConnectionManagerImpl;
import org.example.model.DriverEntity;
import org.example.model.TruckEntity;
import org.example.repository.DriverTruckEntityRepository;
import org.example.repository.TruckEntityRepository;
import org.example.repository.mapper.TruckResultSetMapper;
import org.example.repository.mapper.TruckResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TruckEntityRepositoryImpl implements TruckEntityRepository {
    private TruckResultSetMapper truckResultSetMapper;
    private DriverTruckEntityRepository driverTruckEntityRepository;

    public TruckEntityRepositoryImpl() {
        truckResultSetMapper = new TruckResultSetMapperImpl();
        driverTruckEntityRepository = new DriverTruckEntityRepositoryImpl();
    }

    @Override
    public TruckEntity findById(Integer id) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trucks where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TruckEntity truckEntity = truckResultSetMapper.mapOneResult(resultSet);
            truckEntity.setDrivers(driverTruckEntityRepository.findDriversByTruckId(truckEntity.getId()));
            return truckEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<TruckEntity> findAll() {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trucks");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TruckEntity> truckEntities = truckResultSetMapper.mapListResult(resultSet);
            for (TruckEntity truckEntity : truckEntities) {
                List<DriverEntity> driversByTruckId = driverTruckEntityRepository.findDriversByTruckId(truckEntity.getId());
                truckEntity.setDrivers(driversByTruckId);
            }
            return truckEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM trucks where id=?");
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TruckEntity save(TruckEntity truckEntity) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO trucks (model, number, parking_id) VALUES(?, ?, ?)");
            preparedStatement.setString(1, truckEntity.getModel());
            preparedStatement.setString(2, truckEntity.getNumber());
            preparedStatement.setInt(3, truckEntity.getParkingId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                truckEntity.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Error insert");
            }
            return truckEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public TruckEntity update(TruckEntity truckEntity) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE trucks SET model = ?, number=?, parking_id=? WHERE id = ?");
            preparedStatement.setObject(1, truckEntity.getModel());
            preparedStatement.setObject(2, truckEntity.getNumber());
            preparedStatement.setObject(3,truckEntity.getParkingId());
            preparedStatement.setObject(4,truckEntity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return truckResultSetMapper.mapOneResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
