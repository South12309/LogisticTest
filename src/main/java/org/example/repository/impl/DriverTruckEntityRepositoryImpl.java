package org.example.repository.impl;

import org.example.db.ConnectionManagerImpl;
import org.example.model.DriverEntity;
import org.example.model.TruckEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.DriverTruckEntityRepository;
import org.example.repository.mapper.DriverResultSetMapper;
import org.example.repository.mapper.DriverResultSetMapperImpl;
import org.example.repository.mapper.TruckResultSetMapper;
import org.example.repository.mapper.TruckResultSetMapperImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DriverTruckEntityRepositoryImpl implements DriverTruckEntityRepository {
    private DriverResultSetMapper driverResultSetMapper;
    private TruckResultSetMapper truckResultSetMapper;
    private static DriverTruckEntityRepository INSTANCE = new DriverTruckEntityRepositoryImpl();

    public static DriverTruckEntityRepository getINSTANCE() {
        if (INSTANCE==null) {
            INSTANCE = new DriverTruckEntityRepositoryImpl();
        }
        return INSTANCE;
    }


    private DriverTruckEntityRepositoryImpl() {
        driverResultSetMapper = DriverResultSetMapperImpl.getINSTANCE();
        truckResultSetMapper = TruckResultSetMapperImpl.getINSTANCE();
    }

    @Override
    public List<TruckEntity> findTrucksByDriverId(Integer driverId) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM trucks where id in " +
                            "(SELECT truck_id from drivers_trucks where driver_id=?)");
            preparedStatement.setInt(1, driverId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return truckResultSetMapper.mapListResult(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DriverEntity> findDriversByTruckId(Integer truckId) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM drivers where id in " +
                            "(SELECT driver_id from drivers_trucks where truck_id=?)");
            preparedStatement.setInt(1, truckId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return driverResultSetMapper.mapListResult(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
