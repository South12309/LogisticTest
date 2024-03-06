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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TruckEntityRepositoryImpl implements TruckEntityRepository {
    private static final int DEFAULT_PARKING_ID = 1;
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

    public void setManager(ConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<TruckEntity> findById(Integer id) {
        try (Connection connection = manager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logistic.trucks where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.ofNullable(null);
            }
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logistic.trucks");
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM logistic.trucks where id=?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO logistic.trucks (model, number, parking_id) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, truckEntity.getModel());
            preparedStatement.setString(2, truckEntity.getNumber());
            ParkingEntity parking = truckEntity.getParking();
            preparedStatement.setInt(3, parking==null?DEFAULT_PARKING_ID: parking.getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int anInt = generatedKeys.getInt(1);
                truckEntity.setId(anInt);
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
                            "UPDATE logistic.trucks SET model = ?, number=?, parking_id=? WHERE id = ?");
            preparedStatement.setObject(1, truckEntity.getModel());
            preparedStatement.setObject(2, truckEntity.getNumber());
            ParkingEntity parking = truckEntity.getParking();
            preparedStatement.setObject(3, parking==null?DEFAULT_PARKING_ID:parking.getId());
            preparedStatement.setObject(4, truckEntity.getId());
            preparedStatement.executeUpdate();
            return truckEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TruckEntity> findByParkingId(Integer parkingId) {
        try (Connection connection = manager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logistic.trucks where parking_id=?");
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
