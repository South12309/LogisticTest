package org.example.repository.impl;

import org.example.db.ConnectionManagerImpl;
import org.example.model.DriverEntity;
import org.example.model.TruckEntity;
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
    // private ConnectionManager connectionManager;


    public DriverTruckEntityRepositoryImpl() {
        driverResultSetMapper = new DriverResultSetMapperImpl();
        truckResultSetMapper = new TruckResultSetMapperImpl();
    }

    @Override
    public List<TruckEntity> findTrucksByDriverId(Integer driverId) {
        try (Connection connection = ConnectionManagerImpl.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trucks where id in " +
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM drivers where id in " +
                    "(SELECT driver_id from drivers_trucks where truck_id=?)");
            preparedStatement.setInt(1, truckId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return driverResultSetMapper.mapListResult(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//
//    @Override
//    public boolean deleteById(Integer id) {
//        try (Connection connection = ConnectionManagerImpl.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM drivers where id=?");
//            preparedStatement.setInt(1, id);
//            int result = preparedStatement.executeUpdate();
//            return result > 0;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public List<DriverEntity> findAll() {
//        try (Connection connection = ConnectionManagerImpl.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM drivers");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return driverResultSetMapper.mapListResult(resultSet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public DriverEntity save(DriverEntity driverEntity) {
//        try (Connection connection = ConnectionManagerImpl.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO drivers (fio) VALUES(?)");
//            preparedStatement.setString(1, driverEntity.getFio());
//            preparedStatement.executeUpdate();
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                driverEntity.setId(generatedKeys.getInt(1));
//            } else {
//                throw new SQLException("Error insert");
//            }
//            return driverEntity;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Override
//    public DriverEntity update(DriverEntity driverEntity) {
//        try (Connection connection = ConnectionManagerImpl.getConnection()) {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement(
//                            "UPDATE drivers SET fio = ? WHERE id = ?");
//            preparedStatement.setObject(1, driverEntity.getFio());
//            preparedStatement.setObject(2,driverEntity.getId());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return driverResultSetMapper.mapOneResult(resultSet);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
