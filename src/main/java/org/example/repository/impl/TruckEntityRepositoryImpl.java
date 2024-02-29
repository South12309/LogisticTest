package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.ConnectionManagerImpl;
import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.model.TruckEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.DriverTruckEntityRepository;
import org.example.repository.ParkingEntityRepository;
import org.example.repository.TruckEntityRepository;
import org.example.repository.mapper.ParkingResultSetMapper;
import org.example.repository.mapper.ParkingResultSetMapperImpl;
import org.example.repository.mapper.TruckResultSetMapper;
import org.example.repository.mapper.TruckResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TruckEntityRepositoryImpl implements TruckEntityRepository {
    private ConnectionManager manager;
    private TruckResultSetMapper truckResultSetMapper;
    private DriverTruckEntityRepository driverTruckEntityRepository;

    private static TruckEntityRepository INSTANCE = new TruckEntityRepositoryImpl();

    public static TruckEntityRepository getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TruckEntityRepositoryImpl();
        }
        return INSTANCE;
    }

    private TruckEntityRepositoryImpl() {
        truckResultSetMapper = TruckResultSetMapperImpl.getINSTANCE();
        driverTruckEntityRepository = DriverTruckEntityRepositoryImpl.getINSTANCE();
        manager = ConnectionManagerImpl.getInstance();
    }

    @Override
    public Optional<TruckEntity> findById(Integer id) {
        try (Connection connection = manager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trucks where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TruckEntity truckEntity = truckResultSetMapper.mapOneResult(resultSet);
            ParkingEntity parking = new ParkingEntity();
            parking.setId(resultSet.getInt("parking_id"));
            truckEntity.setParking(parking);
            truckEntity.setDrivers(driverTruckEntityRepository.findDriversByTruckId(truckEntity.getId()));
            return Optional.ofNullable(truckEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<TruckEntity>> findAll() {
        try (Connection connection = manager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trucks");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<TruckEntity> truckEntities = new ArrayList<>();
            while (resultSet.next()) {
                TruckEntity truckEntity = truckResultSetMapper.mapOneResult(resultSet);
                truckEntity.setDrivers(driverTruckEntityRepository.findDriversByTruckId(truckEntity.getId()));
                ParkingEntity parking = new ParkingEntity();
                parking.setId(resultSet.getInt("parking_id"));
                truckEntity.setParking(parking);
                truckEntities.add(truckEntity);
            }
            return Optional.ofNullable(truckEntities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = manager.getConnection()) {
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
        try (Connection connection = manager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO trucks (model, number, parking_id) VALUES(?, ?, ?)");
            preparedStatement.setString(1, truckEntity.getModel());
            preparedStatement.setString(2, truckEntity.getNumber());
            preparedStatement.setInt(3, truckEntity.getParking().getId());
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
        try (Connection connection = manager.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE trucks SET model = ?, number=?, parking_id=? WHERE id = ?");
            preparedStatement.setObject(1, truckEntity.getModel());
            preparedStatement.setObject(2, truckEntity.getNumber());
            preparedStatement.setObject(3, truckEntity.getParking().getId());
            preparedStatement.setObject(4, truckEntity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            return truckResultSetMapper.mapOneResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TruckEntity> findByParkingId(Integer parkingId) {
        try (Connection connection = manager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trucks where parking_id=?");
            preparedStatement.setInt(1, parkingId);
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
}
